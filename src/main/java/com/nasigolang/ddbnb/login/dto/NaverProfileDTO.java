package com.nasigolang.ddbnb.login.dto;

public class NaverProfileDTO {

    private String resultcode;
    private String message;
    private Response response;

    public NaverProfileDTO() {}

    public NaverProfileDTO(String resultcode, String message, Response response) {
        this.resultcode = resultcode;
        this.message = message;
        this.response = response;
    }

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "NaverProfileDTO{" +
                "resultcode='" + resultcode + '\'' +
                ", message='" + message + '\'' +
                ", response=" + response +
                '}';
    }

    public class Response {

        private String id;
        private String gender;
        private String email;

        public Response() {}

        public Response(String id, String gender, String email) {
            this.id = id;
            this.gender = gender;
            this.email = email;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public String toString() {
            return "Response{" +
                    "id='" + id + '\'' +
                    ", gender='" + gender + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
    }
}
