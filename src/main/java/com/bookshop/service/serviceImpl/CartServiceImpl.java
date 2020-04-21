package com.bookshop.service.serviceImpl;

import com.bookshop.common.responseFromServer;
import com.bookshop.dao.CartDao;
import com.bookshop.dao.CartItemDao;
import com.bookshop.entity.Cart;
import com.bookshop.entity.CartItem;
import com.bookshop.entity.User;
import com.bookshop.service.CartService;
import com.bookshop.entity.Page;
import com.bookshop.util.configs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: CartService
 * @Description: TODO
 * @Author: 曾志昊
 * @Date: 2020/4/1 20:02
 */

@Service(value = "CartService")
public class CartServiceImpl implements CartService {

    CartDao cartDao;
    CartItemDao cartItemDao;


    /**
     * @Description: 根据userid获取整个购物车
     * @Date:   2020/4/1 16:45
     */
    public responseFromServer getCart(Map<String,Object> requestMap){
        responseFromServer response = getCartItems(requestMap);
        if(response.isSuccess()){
            Cart newCart = new Cart();
            newCart.setBooks((List<CartItem>)response.getData());
            newCart.setUserId((Integer)requestMap.get("userId"));
            return responseFromServer.success(newCart);
        }else{
            return responseFromServer.error();
        }
    }


    /**
     * @Description: 根据用户id获取所有cartItem
     * @Date:   2020/4/1 16:41
     */
    public responseFromServer getCartItems(Map<String,Object> requestMap){
        if(requestMap.get("userId")==null ){
            return responseFromServer.error("购物车请求参数错误");
        }else{
            return responseFromServer.success(cartItemDao.getCartItems(requestMap));
        }
    }

    /**
     * @Description: 根据userid获取cartItem的分页
     * @Date:   2020/4/1 16:42
     */
    public responseFromServer getCartItemsPage(Map<String,Object> requestMap){
        if(requestMap.get("userId")==null){
            return responseFromServer.error("购物车请求参数错误");
        }else{
            if(requestMap.get("startPage")==null){
                requestMap.put("startPage",1);
            }
            return responseFromServer.success(getPage(requestMap));
        }
    }


    /**
     * @Description: 查询一个分页
     * @Date:   2020/4/1 16:42
     */
    Page getPage(Map<String,Object> queryMap){
        Page<CartItem> page = new Page<CartItem>(configs.pageSize);
        Integer startPage = (Integer)queryMap.get("startPage");
        queryMap.put("startPage",(startPage-1)*configs.pageSize);
        queryMap.put("pageSize",configs.pageSize);
        page.setCurrPage(startPage);
        page.setTotalCount(cartItemDao.count(queryMap));
        page.setTotalPage(((Double)Math.ceil(page.getTotalCount()/configs.pageSize)).intValue());
        page.setLists(cartItemDao.getCartItems(queryMap));
        return page;
    }

    /*根据userid bookid查询完整信息*/
    /*todo 测试没有userid查询出来的结果*/
    /**
     * @Description: 根据userid和bookid获取一个item的完整信息
     * @Date:   2020/4/1 16:43
     */
    public responseFromServer getCartItem(Map<String,Object> reqeustMap){
        if(reqeustMap.get("userId")==null||reqeustMap.get("bookId")==null){
            return responseFromServer.error("请求参数不足");
        }
        CartItem cartItem = cartItemDao.getCartItem(reqeustMap);
        if (cartItem == null) {
            return responseFromServer.error();
        }
        return responseFromServer.success(cartItem);
    }

    /*todo 搜索购物车*/
    /*关联查询*/

    @Transactional
    public responseFromServer insertCartItem(CartItem cartItem){
        try{
            if(cartItem.getBookId()==null||cartItem.getUserId()==null){
                return responseFromServer.error("缺少信息");
            }
            Map<String,Object> queryMap = new HashMap<>();
            queryMap.put("userId",cartItem.getUserId());
            queryMap.put("bookId",cartItem.getBookId());
            CartItem tempCartItem = cartItemDao.getCartItemSimple(queryMap);
            if(tempCartItem!=null/*cartItemDao.count(queryMap)>0*/){
                tempCartItem.setCartNum(tempCartItem.getCartNum()+cartItem.getCartNum());
                if(cartItemDao.updateCartItem(tempCartItem)!=1){
                    throw new Exception();
                }
                return responseFromServer.success("插入成功");
            }else{
                if(cartItemDao.insertCartItem(cartItem)!=1){
                    throw new Exception();
                }
                return responseFromServer.success("插入成功");
            }
        }catch(Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return responseFromServer.error("插入错误");
        }
    }


    @Transactional
    public responseFromServer updateCartItem(CartItem cartItem){
        if(cartItem!=null){
            if(cartItem.getCartNum()>0){
                if (cartItemDao.updateCartItem(cartItem) != 1) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return responseFromServer.error("更新失败");
                }else{
                    return responseFromServer.success();
                }
            }else{
                /*删除*/
                return deleteCartItem(cartItem);
            }
        }else{
            return responseFromServer.error();
        }

    }

    public responseFromServer updateCartItems(List<CartItem> cartItems){
        if(cartItems==null||cartItems.size()==0)return responseFromServer.error("待更新cartItems列表为空");
        int rows = 0;
        for(CartItem cartItem:cartItems){
            if(updateCartItem(cartItem).isSuccess()){
                rows++;
            }
        }
        if (rows < cartItems.size()) {
            return responseFromServer.error(String.format("更新了%d(/%d)",rows,cartItems.size()));
        }else{
            return responseFromServer.success();
        }
    }

    @Transactional
    public responseFromServer deleteCartItem(CartItem cartItem){
        if (cartItem.getUserId() == null || cartItem.getBookId() == null) {
            return responseFromServer.error("缺少删除信息");
        }
        try{
            if(cartItemDao.deleteCartItem(cartItem)!=1){
                throw new Exception();
            }
            return responseFromServer.success();
        }catch(Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return responseFromServer.error();
        }
    }

    public responseFromServer deleteCartItems(List<CartItem> cartItems){
        if(cartItems==null||cartItems.size()==0)return responseFromServer.error("待删除cartItems列表为空");
        int rows = 0;
        for(CartItem cartItem:cartItems){
            if(deleteCartItem(cartItem).isSuccess()){
                rows++;
            }
        }
        if (rows < cartItems.size()) {
            return responseFromServer.error(String.format("删除了了%d(/%d)",rows,cartItems.size()));
        }else{
            return responseFromServer.success();
        }
    }

    public responseFromServer getCartNum(User user){
        Map<String,Object> queryMap = new HashMap<>(0);
        queryMap.put("userId",user.getUserId());
        return responseFromServer.success(cartItemDao.count(queryMap));
    }

    @Autowired
    public CartServiceImpl(CartDao cartDao, CartItemDao cartItemDao){
        this.cartDao = cartDao;
        this.cartItemDao = cartItemDao;
    }
}
