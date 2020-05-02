package com.najiujiangzi.jiangzi.util;

import com.najiujiangzi.jiangzi.enums.FieldType;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 将sql转换为MVC
 */
public class CreateMDMSCBySql {
    public static void main(String[] args) throws Exception {
        String sql = "CREATE TABLE `p_test`  (\n" +
                "  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',\n" +
                "  `user_id` bigint(20) NOT NULL COMMENT '用户id',\n" +
                "  `image_id` bigint(20) NOT NULL COMMENT '图片id',\n" +
                "  `create` datetime(0) NOT NULL COMMENT '点赞时间',\n" +
                "  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否取消点赞',\n" +
                "  PRIMARY KEY (`id`) USING BTREE\n" +
                ")   ";
//        new CreateMDMSCBySql().create(sql);
    }

    //匹配整个ddl，将ddl分为表名，列sql部分，表注释
    private static final Pattern DDL_PATTERN = Pattern.compile("\\s*create\\s+table\\s+(?<tableName>\\S+)[^\\(]*\\((?<columnsSQL>[\\s\\S]+)\\)[^\\)]+?(comment\\s*(=|on\\s+table)\\s*'(?<tableComment>.*?)'\\s*;?)?$", Pattern.CASE_INSENSITIVE);
    //匹配列sql部分，分别解析每一列的列名 类型 和列注释
    private static final Pattern COL_PATTERN = Pattern.compile("\\s*(?<fieldName>\\S+)\\s+(?<fieldType>\\w+)\\s*(?:\\([\\s\\d,]+\\))?((?!comment).)*(comment\\s*'(?<fieldComment>.*?)')?\\s*(,|$)", Pattern.CASE_INSENSITIVE);

    private String tableName;
    private String tableComment;
    private List<String> fieldName = new ArrayList<>();
    private List<String> fieldType = new ArrayList<>();
    private List<String> fieldComment = new ArrayList<>();

    private void parse(String sql) {
        Matcher matcher = DDL_PATTERN.matcher(sql);
        if (matcher.find()) {
            String tableName = matcher.group("tableName");
            String tableComment = matcher.group("tableComment");
//            this.tableName = tableName.replaceAll("`", "").substring(tableName.indexOf("_"));
            this.tableName = tableName;
            this.tableComment = tableComment;
            System.out.println(tableName + "\t\t" + tableComment);
            System.out.println("==========");
            String columnsSQL = matcher.group("columnsSQL");
            if (columnsSQL != null && columnsSQL.length() > 0) {
                Matcher colMatcher = COL_PATTERN.matcher(columnsSQL);
                while (colMatcher.find()) {
                    String fieldName = colMatcher.group("fieldName");
                    String fieldType = colMatcher.group("fieldType");
                    String fieldComment = colMatcher.group("fieldComment");
                    if (!"key".equalsIgnoreCase(fieldType)) {
                        this.fieldName.add(fieldName.replaceAll("`", ""));
                        this.fieldType.add(fieldType);
                        this.fieldComment.add(fieldComment);
                        System.out.println(fieldName + "\t\t" + fieldType + "\t\t" + fieldComment);
                    }
                }
            }
        }
    }

    private void create(String sql) throws Exception {
        parse(sql);
        String[] MVC = new String[]{"model", "dto", "mappers", "service", "controller", "mappers\\\\sql"};
//        String[] MVC = new String[]{"mappers", "mappers\\\\sql"};
        StringBuilder stringBuilder = new StringBuilder();
        String tableName = this.tableName.replaceAll("`", "").substring(this.tableName.indexOf("_"));
        String className = "";
        String url = "../jiangzi\\doc\\";
        String fromUrl = "../jiangzi\\src\\main\\java\\com\\najiujiangzi\\jiangzi\\";
        String[] s1 = tableName.split("_");
        for (String s : s1) {
            className = className + s.substring(0, 1).toUpperCase() + s.substring(1);
        }
        System.out.println(className);
        String dtoName = className + "DTO";
        String mapperName = className + "Mapper";
        String serviceName = className + "Service";
        List<String> fieldName = new ArrayList<>();
        for (String field : this.fieldName) {
            String[] s2 = field.split("_");
            String name = "";
            for (int i = 0; i < s2.length; i++) {
                if (i > 0) {
                    name = name + s2[i].substring(0, 1).toUpperCase() + s2[i].substring(1);
                } else {
                    name = s2[i];
                }
            }
            fieldName.add(name);
        }
        for (String s : MVC) {
            switch (s) {
                case "model":
                    String imp = null;
                    if (fieldType.contains("datetime")) {
                        imp = "import java.time.LocalDateTime;\n\n";
                    }
                    for (int i = 0; i < fieldName.size(); i++) {
                        String javaType = FieldType.getByMysql(fieldType.get(i)).getFieldToJava();
                        stringBuilder.append("\t/**\n").append("\t *").append(fieldComment.get(i)).append("\n").append("\t */\n");
                        stringBuilder.append("\tprivate ").append(javaType).append(" ").append(fieldName.get(i)).append(";\n");
                    }
                    String fields = stringBuilder.toString();
                    stringBuilder.delete(0, stringBuilder.length());
                    for (int i = 0; i < fieldType.size(); i++) {
                        String javaType = FieldType.getByMysql(fieldType.get(i)).getFieldToJava();
                        String methodName = fieldName.get(i).substring(0, 1).toUpperCase() + fieldName.get(i).substring(1);
                        stringBuilder.append("\n\tpublic void set" + methodName + "(" + javaType + " " + fieldName.get(i) + ") {\n");
                        stringBuilder.append("\t\tthis." + fieldName.get(i) + " = " + fieldName.get(i) + ";\n");
                        stringBuilder.append("\t}\n");

                        stringBuilder.append("\tpublic " + javaType + " get" + methodName + "() {\n");
                        stringBuilder.append("\t\treturn this." + fieldName.get(i) + ";\n" + "\t\t}\n");
                    }
                    String setAndGet = stringBuilder.toString();
                    stringBuilder.delete(0, stringBuilder.length());
                    String modelUrl = url + s + ".txt";
                    String model = FileUtils.readFileToString(new File(modelUrl));
                    String newModel = model.replaceAll("@import", imp != null ? imp : "").replaceAll("@className", className)
                            .replaceAll("@fields", fields).replaceAll("@setAndGet", setAndGet);
                    FileUtils.write(new File(fromUrl + s + "\\" + className + ".java"), newModel);
                    break;
                case "dto":
                    String dtolUrl = url + s + ".txt";
                    String dto = FileUtils.readFileToString(new File(dtolUrl));
                    String newDto = dto.replaceAll("@dtoName", dtoName).replaceAll("@modelName", className);
                    FileUtils.write(new File(fromUrl + s + "\\" + dtoName + ".java"), newDto);
                    break;
                case "mappers":
                    String ifs;
                    //StringBuilder insertField = new StringBuilder();
                    //StringBuilder dtoFields = new StringBuilder();
                    StringBuilder set = new StringBuilder();
                    for (int i = 0; i < fieldName.size(); i++) {
                        stringBuilder.append("\t\t\t\" <if test=\\\\\"dto." + fieldName.get(i) + " != null\\\\\">AND " + this.fieldName.get(i) + " = #{dto." + fieldName.get(i) + "}</if>\" +\n");
                        if (!fieldName.get(i).equals("id")) {
                            /*insertField.append("`").append(this.fieldName.get(i)).append("`").append(",");
                            dtoFields.append("#{").append(fieldName.get(i)).append("},");*/
                            set.append(this.fieldName.get(i)).append("=").append("#{").append(fieldName.get(i)).append("},");
                        }
                    }

                    ifs = stringBuilder.toString();
                    stringBuilder.delete(0, stringBuilder.length());

                    String mapperlUrl = url + s + ".txt";
                    String mapper = FileUtils.readFileToString(new File(mapperlUrl));
                    String newMapper = mapper.replaceAll("@dtoName", dtoName).replaceAll("@modelName", className).replaceAll("@mapperName", mapperName)
                            .replaceAll("@tableName", this.tableName).replaceAll("@if", ifs)
                            //.replaceAll("@insertField", insertField.delete(insertField.length() - 1, insertField.length()).toString())
                            //.replaceAll("@dtoFields", dtoFields.delete(dtoFields.length() - 1, dtoFields.length()).toString())
                            .replaceAll("@set", set.delete(set.length() - 1, set.length()).toString());
                    FileUtils.write(new File(fromUrl + s + "\\" + mapperName + ".java"), newMapper);
                    break;
                case "service":
                    String toMapperName = mapperName.substring(0, 1).toLowerCase() + mapperName.substring(1);
                    String serviceUrl = url + s + ".txt";
                    String service = FileUtils.readFileToString(new File(serviceUrl));
                    String newService = service.replaceAll("@dtoName", dtoName).replaceAll("@modelName", className)
                            .replaceAll("@mapperName", mapperName).replaceAll("@toMapperName", toMapperName).replaceAll("@serviceName", serviceName);
                    FileUtils.write(new File(fromUrl + s + "\\" + serviceName + ".java"), newService);
                    break;
                case "controller":
                    String controllerName = className + "Controller";
                    String controllerUrl = url + s + ".txt";
                    String controller = FileUtils.readFileToString(new File(controllerUrl));
                    String newController = controller.replaceAll("@dtoName", dtoName).replaceAll("@modelName", className)
                            .replaceAll("@toModelName", className.substring(0, 1).toLowerCase() + className.substring(1))
                            .replaceAll("@controllerName", controllerName).replaceAll("@serviceName", serviceName)
                            .replaceAll("@toServiceName", serviceName.substring(0, 1).toLowerCase() + serviceName.substring(1));
                    FileUtils.write(new File(fromUrl + s + "//" + controllerName + ".java"), newController);
                    break;
                case "mappers\\\\sql":
                    StringBuilder saveValues = new StringBuilder();
                    StringBuilder updateValue = new StringBuilder();
                    //if (model.getName() != null) {
                    //                        SET("`name` = #{name}");
                    //                    }
                    for (int i = 0; i < fieldName.size(); i++) {
                        String methodName = fieldName.get(i).substring(0, 1).toUpperCase() + fieldName.get(i).substring(1);
                        saveValues.append("\t\t\t\tif (model.get" + methodName + "() != null) {\n")
                                .append("\t\t\t\t\tVALUES(\"`" + this.fieldName.get(i) + "`\", \"#{" + fieldName.get(i) + "}\");\n")
                                .append("\t\t\t\t}\n");
                        updateValue.append("\t\t\t\tif (model.get" + methodName + "() != null) {\n")
                                .append("\t\t\t\t\tSET(\"`" + this.fieldName.get(i) + "` = #{" + fieldName.get(i) + "}\");\n")
                                .append("\t\t\t\t}\n");
                    }
                    /*for (String field : fieldName) {
                        String methodName = field.substring(0, 1).toUpperCase() + field.substring(1);
                        saveValues.append("\t\t\t\tif (model.get" + methodName + "() != null) {\n")
                                .append("\t\t\t\t\tVALUES(\"`" + field + "`\", \"#{" + field + "}\");\n")
                                .append("\t\t\t\t\t}\n");
                    }*/
                    String sqlName = className + "Sql";
                    String sqlUrl = url + "sql.txt";
                    String sqlClass = FileUtils.readFileToString(new File(sqlUrl));
                    String newSqlClass = sqlClass.replaceAll("@tableName", this.tableName).replaceAll("@modelName", className)
                            .replaceAll("@saveValues", String.valueOf(saveValues))
                            .replaceAll("@updateValue", String.valueOf(updateValue));
                    FileUtils.write(new File(fromUrl + s + "//" + sqlName + ".java"), newSqlClass);
                    break;
            }
        }
    }

}
