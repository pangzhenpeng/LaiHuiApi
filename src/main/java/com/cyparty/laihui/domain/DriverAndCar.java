package com.cyparty.laihui.domain;

/**
 * Created by zhu on 2016/8/3.
 * 需要  pc_car_owner_info  pc_driver_publish_info 两个表关联
 */
public class DriverAndCar {
    private int r_id;
    private int user_id;
    //是否可用
    private int is_enable;
    //状态
    private int status;
    //出发时间 车辆型号 车辆颜色
    private String start_time, car_type, car_color;
    //始发地
    private String boarding_point;
    //目的地
    private String breakout_point;
    //车辆座位数
    private int init_seats;
    //车辆剩余座位数
    private int current_seats;
    //创建时间
    private String create_time;

    private String points;
    //描述
    private String description;

    //手机号
    //金额
    private double price;
    //起点县
    String departure_county;

    private String mobile;
    //车主名
    String driving_name;
    //车主用户名
    String user_name;
    //车主头像
    String user_avatar;
    private String remark;
    private int flag;
    private String suitability;
    //起点城市代码
    private int departure_city_code = 0;
    //终点城市代码
    private int destination_city_code = 0;
    //起点地址代码
    private int departure_address_code = 0;
    private int destination_address_code = 0;
    private double start_point_distance;
    private double end_point_distance;

    private String boarding_latitude;//出发地纬度
    private String boarding_longitude;//出发地经度
    private String breakout_latitude;//目的地纬度
    private String breakout_longitude;//目的地经度
    private int departure_code;//出发地code前四位
    private int destination_code;//目的地code前四位
    private double s_distance;
    private double e_distance;

    public double getS_distance() {
        return s_distance;
    }

    public void setS_distance(double s_distance) {
        this.s_distance = s_distance;
    }

    public double getE_distance() {
        return e_distance;
    }

    public void setE_distance(double e_distance) {
        this.e_distance = e_distance;
    }

    public double getStart_point_distance() {
        return start_point_distance;
    }

    public void setStart_point_distance(double start_point_distance) {
        this.start_point_distance = start_point_distance;
    }

    public double getEnd_point_distance() {
        return end_point_distance;
    }

    public void setEnd_point_distance(double end_point_distance) {
        this.end_point_distance = end_point_distance;
    }

    public String getSuitability() {
        return suitability;
    }

    public void setSuitability(String suitability) {
        this.suitability = suitability;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }


    public int getR_id() {
        return r_id;
    }

    public void setR_id(int r_id) {
        this.r_id = r_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getIs_enable() {
        return is_enable;
    }

    public void setIs_enable(int is_enable) {
        this.is_enable = is_enable;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getBoarding_point() {
        return boarding_point;
    }

    public void setBoarding_point(String boarding_point) {
        this.boarding_point = boarding_point;
    }

    public String getBreakout_point() {
        return breakout_point;
    }

    public void setBreakout_point(String breakout_point) {
        this.breakout_point = breakout_point;
    }

    public int getInit_seats() {
        return init_seats;
    }

    public void setInit_seats(int init_seats) {
        this.init_seats = init_seats;
    }

    public int getCurrent_seats() {
        return current_seats;
    }

    public void setCurrent_seats(int current_seats) {
        this.current_seats = current_seats;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDeparture_county() {
        return departure_county;
    }

    public void setDeparture_county(String departure_county) {
        this.departure_county = departure_county;
    }

    public String getDriving_name() {
        return driving_name;
    }

    public void setDriving_name(String driving_name) {
        this.driving_name = driving_name;
    }

//    public String getUser_name() {
//        return user_name;
//    }
//
//    public void setUser_name(String user_name) {
//        this.user_name = user_name;
//    }
//
//    public String getUser_avatar() {
//        return user_avatar;
//    }
//
//    public void setUser_avatar(String user_avatar) {
//        this.user_avatar = user_avatar;
//    }

    public int getDeparture_city_code() {
        return departure_city_code;
    }

    public void setDeparture_city_code(int departure_city_code) {
        this.departure_city_code = departure_city_code;
    }

    public int getDestination_city_code() {
        return destination_city_code;
    }

    public void setDestination_city_code(int destination_city_code) {
        this.destination_city_code = destination_city_code;
    }

    public int getDeparture_address_code() {
        return departure_address_code;
    }

    public void setDeparture_address_code(int departure_address_code) {
        this.departure_address_code = departure_address_code;
    }

    public int getDestination_address_code() {
        return destination_address_code;
    }

    public void setDestination_address_code(int destination_address_code) {
        this.destination_address_code = destination_address_code;
    }

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }

    public String getCar_color() {
        return car_color;
    }

    public void setCar_color(String car_color) {
        this.car_color = car_color;
    }

    public String getBoarding_latitude() {
        return boarding_latitude;
    }

    public void setBoarding_latitude(String boarding_latitude) {
        this.boarding_latitude = boarding_latitude;
    }

    public String getBoarding_longitude() {
        return boarding_longitude;
    }

    public void setBoarding_longitude(String boarding_longitude) {
        this.boarding_longitude = boarding_longitude;
    }

    public String getBreakout_latitude() {
        return breakout_latitude;
    }

    public void setBreakout_latitude(String breakout_latitude) {
        this.breakout_latitude = breakout_latitude;
    }

    public String getBreakout_longitude() {
        return breakout_longitude;
    }

    public void setBreakout_longitude(String breakout_longitude) {
        this.breakout_longitude = breakout_longitude;
    }

    public int getDeparture_code() {
        return departure_code;
    }

    public void setDeparture_code(int departure_code) {
        this.departure_code = departure_code;
    }

    public int getDestination_code() {
        return destination_code;
    }

    public void setDestination_code(int destination_code) {
        this.destination_code = destination_code;
    }
}
