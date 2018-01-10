package mivs.users;

public enum Gender {


        MALE ("Male"),
        FEMALE ("Female");


        private final String role;

        Gender(String s) {
            this.role = s;
        }
        public String get(){
            return role;
        }


}
