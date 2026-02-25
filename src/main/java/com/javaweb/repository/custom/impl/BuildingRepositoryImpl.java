package com.javaweb.repository.custom.impl;
import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.utils.StringUtils;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    private void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
        if (buildingSearchBuilder.getAreaFrom() != null || buildingSearchBuilder.getAreaTo() != null) {
            sql.append(" INNER JOIN rentarea ra ON b.id = ra.buildingid ");
        }
        if (buildingSearchBuilder.getStaffId() != null) {
            sql.append(" INNER JOIN assignmentbuilding ab ON b.id = ab.buildingid ");
        }
    }
    private void queryNormal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
        if (StringUtils.checkString(buildingSearchBuilder.getName())) {
            where.append(" AND b.name LIKE '%")
                    .append(buildingSearchBuilder.getName())
                    .append("%'");
        }
        if (StringUtils.checkString(buildingSearchBuilder.getDistrict())) {
            where.append(" AND b.district = '")
                    .append(buildingSearchBuilder.getDistrict())
                    .append("'");
        }
        if (StringUtils.checkString(buildingSearchBuilder.getManagerPhoneNumber())) {
            where.append(" AND b.managerphone LIKE '%")
                    .append(buildingSearchBuilder.getManagerPhoneNumber())
                    .append("%'");
        }
        if (StringUtils.checkString(buildingSearchBuilder.getWard())) {
            where.append(" AND b.ward LIKE '%")
                    .append(buildingSearchBuilder.getWard())
                    .append("%'");
        }
        if (StringUtils.checkString(buildingSearchBuilder.getStreet())) {
            where.append(" AND b.street LIKE '%")
                    .append(buildingSearchBuilder.getStreet())
                    .append("%'");
        }
        if (StringUtils.checkString(buildingSearchBuilder.getManagerName())) {
            where.append(" AND b.managername LIKE '%")
                    .append(buildingSearchBuilder.getManagerName())
                    .append("%'");
        }
        if (buildingSearchBuilder.getFloorArea() != null) {
            where.append(" AND b.floorarea = ")
                    .append(buildingSearchBuilder.getFloorArea());
        }
        if (buildingSearchBuilder.getNumberOfBasement() != null) {
            where.append(" AND b.numberofbasement = ")
                    .append(buildingSearchBuilder.getNumberOfBasement());
        }
        if (buildingSearchBuilder.getStaffId() != null) {
            where.append(" AND ab.staffid = ")
                    .append(buildingSearchBuilder.getStaffId());
        }
    }
    private void querySpecial(BuildingSearchBuilder buildingSearchbuilder, StringBuilder where) {
        if (buildingSearchbuilder.getAreaFrom() != null) {
            where.append(" AND ra.value >= ").append(buildingSearchbuilder.getAreaFrom());
        }
        if (buildingSearchbuilder.getAreaTo() != null) {
            where.append(" AND ra.value <= ").append(buildingSearchbuilder.getAreaTo());
        }
        if (buildingSearchbuilder.getRentPriceFrom() != null) {
            where.append(" AND b.rentprice >= ").append(buildingSearchbuilder.getRentPriceFrom());
        }
        if (buildingSearchbuilder.getRentPriceTo() != null) {
            where.append(" AND b.rentprice <= ").append(buildingSearchbuilder.getRentPriceTo());
        }
        if (buildingSearchbuilder.getTypeCode() != null
                && !buildingSearchbuilder.getTypeCode().isEmpty()) {
            where.append(" AND (");
            List<String> types = buildingSearchbuilder.getTypeCode();
            for (int i = 0; i < types.size(); i++) {
                where.append(" b.type LIKE '%").append(types.get(i)).append("%'");
                    if (i < types.size() - 1) {
                    where.append(" OR ");
                }
            }
            where.append(")");
        }
    }
    @Override
    public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
        StringBuilder sql = new StringBuilder("SELECT DISTINCT b.* FROM building b ");
        joinTable(buildingSearchBuilder, sql);
        StringBuilder where = new StringBuilder(" WHERE 1=1 ");
        queryNormal(buildingSearchBuilder, where);
        querySpecial(buildingSearchBuilder, where);
        sql.append(where);
        System.out.println("SQL = " + sql);
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }
}
