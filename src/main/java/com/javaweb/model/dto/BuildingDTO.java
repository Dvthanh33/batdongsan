package com.javaweb.model.dto;


import java.util.List;

public class BuildingDTO extends AbstractDTO{
    private String name;
    private String address;
    private Long numberOfBasement;
    private String district;
    private String managerName;
    private String managerPhone;
    private Integer floorArea;
    private Integer emptyArea;
    private String rentArea;
    private String serviceFee;
    private String ward;
    private String street;
    private String direction;
    private String level;
    private String areaFrom;
    private String areaTo;
    private String rentPriceFrom;
    private String rentPriceTo;
    private Integer staffId;
    private Integer rentPrice;
    private Long brokerageFee;
    private List<String> typeCode;
    private String structure;
    private String rentPriceDescription;

    public String getRentPriceDescription() {
        return rentPriceDescription;
    }

    public void setRentPriceDescription(String rentPriceDescription) {
        this.rentPriceDescription = rentPriceDescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getNumberOfBasement() {
        return numberOfBasement;
    }

    public void setNumberOfBasement(Long numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public Integer getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(Integer floorArea) {
        this.floorArea = floorArea;
    }

    public Integer getEmptyArea() {
        return emptyArea;
    }

    public void setEmptyArea(Integer emptyArea) {
        this.emptyArea = emptyArea;
    }

    public String getRentArea() {
        return rentArea;
    }
    public void setRentArea(String rentArea) {
        this.rentArea = rentArea;
    }
    public String getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAreaFrom() {
        return areaFrom;
    }

    public void setAreaFrom(String areaFrom) {
        this.areaFrom = areaFrom;
    }

    public String getAreaTo() {
        return areaTo;
    }

    public void setAreaTo(String areaTo) {
        this.areaTo = areaTo;
    }

    public String getRentPriceFrom() {
        return rentPriceFrom;
    }

    public void setRentPriceFrom(String rentPriceFrom) {
        this.rentPriceFrom = rentPriceFrom;
    }

    public String getRentPriceTo() {
        return rentPriceTo;
    }

    public void setRentPriceTo(String rentPriceTo) {
        this.rentPriceTo = rentPriceTo;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Integer getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Integer rentPrice) {
        this.rentPrice = rentPrice;
    }

    public Long getBrokerageFee() {
        return brokerageFee;
    }

    public void setBrokerageFee(Long brokerageFee) {
        this.brokerageFee = brokerageFee;
    }

    public List<String> getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(List<String> typeCode) {
        this.typeCode = typeCode;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }
}