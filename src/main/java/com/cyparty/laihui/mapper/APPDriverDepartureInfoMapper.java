package com.cyparty.laihui.mapper;


import com.cyparty.laihui.domain.DepartureInfo;
import com.cyparty.laihui.utilities.Utils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by zhu on 2015/12/29.
 */
public class APPDriverDepartureInfoMapper implements RowMapper<DepartureInfo> {

    public DepartureInfo mapRow(ResultSet resultSet, int i) throws SQLException {
        DepartureInfo departure=new DepartureInfo();

        departure.setR_id(resultSet.getInt("_id"));
        departure.setUser_id(resultSet.getInt("user_id"));
        departure.setStart_time(Utils.checkTime(resultSet.getString("departure_time")));

        departure.setInit_seats(resultSet.getInt("init_seats"));
        departure.setMobile(Utils.checkNull(resultSet.getString("mobile")));
        departure.setCurrent_seats(resultSet.getInt("current_seats"));
        departure.setPrice(resultSet.getDouble("price"));

        /*departure.setPoints(Utils.checkNull(resultSet.getString("points")));
        departure.setDescription(Utils.checkNull(resultSet.getString("description")));*/
        departure.setCreate_time(Utils.checkTime(resultSet.getString("create_time")));
        departure.setIs_enable(resultSet.getInt("is_enable"));
        String name= Utils.checkNull(resultSet.getString("user_name"));
        String idsn= Utils.checkNull(resultSet.getString("user_idsn"));

        if(!name.isEmpty()) {
            String endName = "";
            String sexNum ="";
            if (!idsn.isEmpty()) {
                int length = idsn.length();
                switch (length){
                    case 15:
                        if (!idsn.substring(14,15).matches("[a-zA-Z]")){
                            sexNum = idsn.substring(14,15);
                        }
                        break;
                    case 18:
                        sexNum = idsn.substring(16,17);
                        break;
                    default:
                        sexNum = "1";
                }
                if (!sexNum.isEmpty()) {
                    if (Integer.parseInt(sexNum) % 2 == 1) {
                        endName = "先生";
                    } else {
                        endName = "女士";
                    }
                }
            }
            if (name.length() <= 3) {
                name = name.substring(0, 1) + endName;
            } else {
                name = name.substring(0, 2) + endName;
            }
        }
        departure.setUser_name(name);

        departure.setUser_avatar(Utils.checkNull(resultSet.getString("user_avatar")));
        //新增备注字段
        departure.setRemark(Utils.checkNull(resultSet.getString("remark")));
        departure.setSource(resultSet.getInt("source"));
       /* departure.setStatus(resultSet.getInt("info_status"));*/
        departure.setBoarding_point(resultSet.getString("boarding_point"));
        departure.setBreakout_point(resultSet.getString("breakout_point"));
        return departure;
    }
}
