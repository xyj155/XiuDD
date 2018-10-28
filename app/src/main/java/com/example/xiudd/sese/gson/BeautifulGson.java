package com.example.xiudd.sese.gson;

import java.util.List;

/**
 * Created by 徐易杰 on 2018/10/19.
 */

public class BeautifulGson<T> {

        /**
         * per_page : 10
         * current_page : 2
         * has_more : true
         * next_item : [{"id":21,"pic_url":"http://vt.pdyimg.xyz/uploadfile/2018/0819/20180819094938882.jpg"}]
         * data : [{"id":11,"pic_url":"http://vt.pdyimg.xyz/uploadfile/2018/0819/20180819095010712.jpg"},{"id":12,"pic_url":"http://vt.pdyimg.xyz/uploadfile/2018/0819/20180819095005895.jpg"},{"id":13,"pic_url":"http://vt.pdyimg.xyz/uploadfile/2018/0819/20180819095003291.jpg"},{"id":14,"pic_url":"http://vt.pdyimg.xyz/uploadfile/2018/0819/20180819094958617.jpg"},{"id":15,"pic_url":"http://vt.pdyimg.xyz/uploadfile/2018/0819/20180819094956586.jpg"},{"id":16,"pic_url":"http://vt.pdyimg.xyz/uploadfile/2018/0819/20180819094952994.jpg"},{"id":17,"pic_url":"http://vt.pdyimg.xyz/uploadfile/2018/0819/20180819094951997.jpg"},{"id":18,"pic_url":"http://vt.pdyimg.xyz/uploadfile/2018/0819/20180819094946674.jpg"},{"id":19,"pic_url":"http://vt.pdyimg.xyz/uploadfile/2018/0819/20180819094944344.jpg"},{"id":20,"pic_url":"http://vt.pdyimg.xyz/uploadfile/2018/0819/20180819094940759.jpg"}]
         */

        private int per_page;
        private int current_page;
        private boolean has_more;
        private List<T> data;

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public boolean isHas_more() {
            return has_more;
        }

        public void setHas_more(boolean has_more) {
            this.has_more = has_more;
        }



        public List<T> getData() {
            return data;
        }

        public void setData(List<T> data) {
            this.data = data;
        }




}
