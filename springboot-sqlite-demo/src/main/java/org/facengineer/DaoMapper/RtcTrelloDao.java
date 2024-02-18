package org.facengineer.DaoMapper;

import org.facengineer.Model.RtcTrelloModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;
import java.util.List;

@Mapper
public interface RtcTrelloDao {
    @Select("select * from usermapping")
    public List<RtcTrelloModel> GetUserMapping();

    @Insert("insert into usermapping values(#{trelloname},#{rtcname})")
    public boolean InsertIntoUserInfo(@Param("trelloname") String TrelloName, @Param("rtcname") String RtcName);
    
    @Delete("DELETE FROM usermapping WHERE RtcName = #{rtcname}")
    public boolean DelUserInfo(@Param("trelloname") String TrelloName, @Param("rtcname") String RtcName);
    
    
    //log-in validation
    @Select("select * FROM usermapping WHERE RtcName = #{rtcname}")
    public boolean Validate(@Param("trelloname") String TrelloName, @Param("rtcname") String RtcName);
    
    @Select("SELECT * FROM usermapping WHERE RtcName = #{rtcname}")
    public RtcTrelloModel getUserMappingByRtcName(@Param("rtcname") String rtcname);


}
