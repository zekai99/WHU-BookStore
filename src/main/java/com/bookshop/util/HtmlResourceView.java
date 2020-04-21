package com.bookshop.util;

import org.springframework.web.servlet.view.InternalResourceView;

import java.io.File;
import java.util.Locale;

/**
 * @ClassName: HtmlResourceView
 * @Description:
 * @Author: 曾志昊
 * @Date: 2020/3/30 19:53
 */

public class HtmlResourceView extends InternalResourceView {
    @Override
    public boolean checkResource(Locale locale) {
        File file = new File(this.getServletContext().getRealPath("/") + getUrl());
        return file.exists();// 判断该页面是否存在
    }
}
