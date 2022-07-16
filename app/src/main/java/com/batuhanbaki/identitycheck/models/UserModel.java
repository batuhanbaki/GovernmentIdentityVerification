package com.batuhanbaki.identitycheck.models;

public class UserModel {
    private final long identityNumber;
    private final String name;
    private final String surname;
    private final int birthYear;
    private final int birthMonth;
    private final int birthDay;

    private UserModel(Builder builder) {
        this.identityNumber = builder.identityNumber;
        this.name = builder.name;
        this.surname = builder.surname;
        this.birthYear = builder.birthYear;
        this.birthMonth = builder.birthMonth;
        this.birthDay = builder.birthDay;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "identityNumber=" + identityNumber +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthYear=" + birthYear +
                ", birthMonth=" + birthMonth +
                ", birthDay=" + birthDay +
                '}';
    }

    public int getBirthMonth() {
        return birthMonth;
    }

    public int getBirthDay() {
        return birthDay;
    }

    public String getIdentityNumber() {
        return String.valueOf(identityNumber);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getBirthYear() {
        return String.valueOf(birthYear);
    }


    public static class Builder {
        private long identityNumber;
        private String name;
        private String surname;
        private int birthYear;
        private int birthMonth;
        private int birthDay;

        public Builder() {

        }

        public Builder identityNumber(long identityNumber) {
            this.identityNumber = identityNumber;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder birthYear(int birthYear) {
            this.birthYear = birthYear;
            return this;
        }

        public Builder birthMonth(int birthMonth) {
            this.birthMonth = birthMonth;
            return this;
        }

        public Builder birthDay(int birthDay) {
            this.birthDay = birthDay;
            return this;
        }

        public UserModel build() {
            return new UserModel(this);
        }
    }

}
