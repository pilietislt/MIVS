package mivs.users;

public enum Gender {


        MALE (2),
        FEMALE (1);


        private final int gender;

        Gender(int s) {
            this.gender = s;
        }
        public int get(){
            return gender;
        }


}
