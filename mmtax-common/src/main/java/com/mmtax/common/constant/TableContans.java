package com.mmtax.common.constant;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/5/30 12:46
 */
public class TableContans {
    /**
     * 多租户需要过滤的表
     */
    public static final String ENCLUDE_TABLES="sys_user,sys_job,sys_dict_type,sys_dict_data,qrtz_triggers," +
            "qrtz_simprop_triggers, qrtz_simple_triggers,qrtz_scheduler_state,qrtz_paused_trigger_grps,qrtz_locks," +
            "qrtz_job_details, qrtz_fired_triggers,qrtz_cron_triggers,qrtz_calendars,qrtz_blob_triggers,sys_user_online,"
            + "institution_info,tables,columns,tbl_certificate_type";

}
