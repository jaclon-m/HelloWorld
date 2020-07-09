package com.jaclon.validator.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.jaclon.validator.validator.MyAnnotation;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 贷前数据历史提交记录详情表
 * </p>
 *
 * @author jaclon
 * @since 2020-03-18
 */
@Data
public class SubmitRequestDetailInfo extends BaseValidateObj {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    /**
     * 操作代码
     */
    @Length(min = 10,max = 16)
    private String opCode;

    /**
     * 记录生成时间
     */
    @NotNull
    private String uploadTs;

    /**
     * 是否是助贷业务
     */
    private Integer isFinTechAgencyBusiness;

    /**
     * 信贷业务申请时间
     */
    private String applyDate;

    /**
     * 信贷业务申请编号
     */
    private String applyId;

    /**
     * 信贷业务申请类型
     */
    private Integer applyType;

    /**
     * 姓名
     */
    @MyAnnotation(value = "ssss",field = "fffff")
    private String name;

    /**
     * 证件类型
     */
    private Integer idType;

    /**
     * 证件号码
     */
    private String pid;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 学历
     */
    private Integer eduBackground;

    /**
     * 学位
     */
    private Integer degree;

    /**
     * 信贷业务担保类型
     */
    private Integer guaranteeType;

    /**
     * 贷款用途
     */
    private Integer loanPurpose;

    /**
     * 客户类型
     */
    private Integer customType;

    /**
     * 申请贷款金额
     */
    private BigDecimal applyAmount;

    /**
     * 居住状况
     */
    private Integer livingCondition;

    /**
     * 婚姻状况
     */
    private Integer marriageStatus;

    /**
     * 本次校验结果：0通过 1不通过
     */
    private Integer checkSuccess;

    /**
     * 本次上报结果：0成功 1失败
     */
    private Integer submitSuccess;

    /**
     * 原始上报数据详情
     */
    private String rawData;

    /**
     * 创建时间
     */
    private LocalDateTime createAt;

    /**
     * 更新时间
     */
    private LocalDateTime updateAt;
}
