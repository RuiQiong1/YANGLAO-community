package com.example.yanglaocommunity;

public class UserInfo {
        private String name;
        private String phone;
        private String memberId;

        public UserInfo() {}

        public UserInfo(String name, String phone, String memberId) {
            this.name = name;
            this.phone = phone;
            this.memberId = memberId;
        }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }
        public String getMemberId() { return memberId; }
        public void setMemberId(String memberId) { this.memberId = memberId; }
}

