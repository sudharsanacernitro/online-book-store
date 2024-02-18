package org.facengineer.RtcTrelloWeb;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.facengineer.DaoMapper.RtcTrelloDao;
import org.facengineer.Model.RtcTrelloModel;
import org.facengineer.PublicTools.BaseResponse;
import org.facengineer.PublicTools.LOG;
import org.facengineer.PublicTools.RespCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class BUserOperation {
    static LOG _logger = new LOG(BUserOperation.class);
    private RtcTrelloDao rtcTrelloDao;

    BUserOperation(RtcTrelloDao rtcTrelloDao) {
        this.rtcTrelloDao = rtcTrelloDao;
    }

    @RequestMapping(value = "userpost/")
    public String UserAdd(@RequestParam(value = "rtcname", required = true) String rtcname,
                                @RequestParam(value = "trelloname", required = true) String trelloname) {
        RtcTrelloModel userMapping = rtcTrelloDao.getUserMappingByRtcName(rtcname);
        if (userMapping != null) {
        	return "false";
        } else {
        	if(rtcTrelloDao.InsertIntoUserInfo(trelloname,rtcname) )
        	{
            System.out.println("Added =>"+rtcname+"  "+trelloname);

        return "true";
        	}
        	return "false";
    }
    }
    @RequestMapping(value = "summa/")
    public String log_in_validation(@RequestParam(value = "rtcname", required = true) String rtcname,
                                    @RequestParam(value = "trelloname", required = true) String trelloname) {
        System.out.println(rtcname);
               
            RtcTrelloModel userMapping = rtcTrelloDao.getUserMappingByRtcName(rtcname);
            if (userMapping != null) {
            	if(userMapping.getTrelloname().equals(trelloname))
            	{
                    return "true"; 

            	}
                System.out.println("wrong-password");
                return "wrong";
            } else {
                System.out.println("No user mapping found for the given RTC name.");
            }
        return "error";
    }
    
    @RequestMapping(value = "del/")
    public BaseResponse del(@RequestParam(value = "rtcname", required = true) String rtcname,
                                @RequestParam(value = "trelloname", required = true) String trelloname) {
        BaseResponse response;
        if (rtcTrelloDao.DelUserInfo(trelloname,rtcname))
            response = new BaseResponse(RespCode.SUCCESS, "Success");
        else
            response = new BaseResponse(RespCode.SUCCESS, "Error");
        return response;
    }

    public static void OutputUserList(List<RtcTrelloModel> UserList) {
        for (RtcTrelloModel _model : UserList) {
            _logger.info(_model.getRtcname() + "|" + _model.getTrelloname()+"|");
        }
    }
}
