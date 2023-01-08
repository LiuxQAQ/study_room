package com.lx.constants;

public class SystemConstants {


    /**
     * 文章是草稿
     */
    public static final int ARTICLE_STATUS_DRAFT = 1;
    /**
     * 文章是正常分布状态
     */
    public static final int ARTICLE_STATUS_NORMAL = 0;
    public static final int ARTICLE_STATUS_NOT_NORMAL = 1;

    /**
     * 分页页数
     */
    public static final int ARTICLE_PAGE_CURRENT = 1;

    /**
     * 分页每页条目
     */
    public static final int ARTICLE_PAGE_SIZE = 10;

    /**
     * 文章分类正常状态
     */
    public static final Integer ROOM_STATUS_NORMAL = 0;

    public static final String Link_STATUS_NORMAL = "0";

    public static final int COMMENT_ROOT_ID = -1;

    public static final String COMMENT_TYPE_ARTICLE = "0";
    public static final String COMMENT_TYPE_FRIENDLINK = "1";

    public static final String APP_LOGIN_ID = "appLogin:";

    public static final String ADMIN_LOGIN_ID = "adminLogin:";

    public static final String HTTP = "http://";

    //    浏览量
    public static final String ARTICLE_VIEW_COUNT = "article:viewCount";

    /**
     * @author lx
     * @date 2022/10/4 20:56
     * @param null
     * @description 浏览量自增量
     */
    public static final Integer UPDATE_VIEW_COUNT = 1;


    /**
     * @author lx
     * @date 2022/10/4 20:55
     * @param null
     * @description 团队名称
     */
    public static final String CONTACT_NAME = "join";

    /**
     * @author lx
     * @date 2022/10/4 21:00
     * @param null
     * @description 团队url
     */
    public static final String CONTACT_URL = "123.57.74.173";

    /**
     * @author lx
     * @date 2022/10/4 21:01
     * @param null
     * @description 团队email
     */
    public static final String CONTACT_EMAIL = "1716193210@qq.com";

    /**
     * @author lx
     * @date 2022/10/5 20:41
     * @param null
     * @description\
     */
    public static final String SYSTEM_MENUS = "C";

    /**
     * @author lx
     * @date 2022/10/5 20:42
     * @param null
     * @description
     */
    public static final String SYSTEM_BUTTON = "F";

    /**
     * @author lx
     * @date 2022/10/5 20:45
     * @param null
     * @description
     */
    public static final String MENU_STATUS = "0";

    /**
     * @author lx
     * @date 2022/10/5 21:54
     * @param null
     * @description
     */
    public static final String SYSTEM_ROLE_KEY = "admin";

    public static final int TAG_DELETE = 1;

    public static final String TAG_STATUS_NORMAL = "0";

    public static final String FILE_NAME_CATEGORY = "博客分类.xlsx";


    public static final String ADMIN = "1";

    public static final String MENU_DELETE = "1";

    /**
     * @author lx
     * @date 2022/10/30 19:55
     * @param null
     * @description 微信key
     */
    public static final String WX_SESSION_ID = "wx_session_id_";

    public static final int ROOM_STATE_NORMAL = 0;
    public static final int ROOM_STATE_DISABLE = 1;
    public static final int ROOM_STATE_APPLY = 2;
    public static final int ROOM_STATE_WAIT = 3;

    public static final int MANAGEMENT_STATE_WAIT = 0;
    public static final int MANAGEMENT_STATE_PASS = 1;
    public static final int MANAGEMENT_STATE_NO = 2;


    public static final int ORDER_STATE_ALL = 0;
    public static final int ORDER_STATE_PAY = 1;
    public static final int ORDER_STATE_NO = 2;





}