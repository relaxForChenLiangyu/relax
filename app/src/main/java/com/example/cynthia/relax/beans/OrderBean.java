package  com.example.cynthia.relax.beans;

import java.io.Serializable;

public class OrderBean implements Serializable{
    private Integer orderId;

    private Integer patientId;

    private String patientName;

    private Integer specialistId;

    private String partnerName;

    private String specialistName;

    private String specialistPhone;

    private Integer typeId;

    private Integer orderStatus;

    private Integer consultingWay;

    private long pubishTime;

    private long completeTime;

    private Double sum;

    private String location;

    private String description;

    private long consultingStartTime;

    private long consultingFinishTime;

    public OrderBean() {

    }

    public OrderBean(Integer typeId, Integer orderStatus, Double sum, String partnerName) {
        this.typeId = typeId;
        this.orderStatus = orderStatus;
        this.sum = sum;
        this.partnerName = partnerName;
    }


    public long getConsultingFinishTime() {
        return consultingFinishTime;
    }

    public void setConsultingFinishTime(long consultingFinishTime) {
        this.consultingFinishTime = consultingFinishTime;
    }

    public long getConsultingStartTime() {

        return consultingStartTime;
    }

    public void setConsultingStartTime(long consultingStartTime) {
        this.consultingStartTime = consultingStartTime;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {

        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getSum() {

        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public long getCompleteTime() {

        return completeTime;
    }

    public void setCompleteTime(long completeTime) {
        this.completeTime = completeTime;
    }

    public long getPubishTime() {

        return pubishTime;
    }

    public void setPubishTime(long pubishTime) {
        this.pubishTime = pubishTime;
    }

    public Integer getConsultingWay() {

        return consultingWay;
    }

    public void setConsultingWay(Integer consultingWay) {
        this.consultingWay = consultingWay;
    }

    public Integer getOrderStatus() {

        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getTypeId() {

        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getSpecialistId() {

        return specialistId;
    }

    public void setSpecialistId(Integer specialistId) {
        this.specialistId = specialistId;
    }

    public Integer getPatientId() {

        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getOrderId() {

        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getSpecialistPhone() {
        return specialistPhone;
    }

    public void setSpecialistPhone(String specialistPhone) {
        this.specialistPhone = specialistPhone;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getSpecialistName() {
        return specialistName;
    }

    public void setSpecialistName(String specialistName) {
        this.specialistName = specialistName;
    }
}


