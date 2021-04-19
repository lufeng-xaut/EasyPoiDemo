import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.util.PoiPublicUtil;
import cn.afterturn.easypoi.word.WordExportUtil;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EasyPoiDemo {

    public static void main(String[] args) {

        importExcel();

    }

    static void exportExcel() {
        // 文件路径配置
        String tempDirPath = "d:/";
        String targetFilePath = tempDirPath + File.separator + "专家评审签字表.xlsx"; // 文件临时存放位置
        String excelTemplatePath = "d:/template.xlsx";

        // 导出模板和参数
        TemplateExportParams params = new TemplateExportParams(excelTemplatePath);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("name", "111");
        paramMap.put("categoryName", "111");
        paramMap.put("status", "111");

        // 导出
        Workbook workbook = ExcelExportUtil.exportExcel(params, paramMap);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(targetFilePath);
            workbook.write(fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void exportWord(){
        List<ScoreItem> itemList = new ArrayList<ScoreItem>();
        ScoreItem scoreItem = new ScoreItem();
        scoreItem.setName("管理能力");
        scoreItem.setComment("1.具备完整孵化器硬件配套的情况，包括场地、公共服务等。2.具备完整孵化器软件配套的情况，包括提供创业服务等。3.拥有一支较高水平的孵化器运营团队，且达到一定的管理水平、专业化水平");
        scoreItem.setWeight(40);
        scoreItem.setScore(40);

        ScoreItem scoreItem2 = new ScoreItem();
        scoreItem2.setName("投资能力");
        scoreItem2.setComment("孵化器运营方具有一定的投资服务能力");
        scoreItem2.setWeight(10);
        scoreItem2.setScore(10);
        itemList.add(scoreItem);
        itemList.add(scoreItem2);

        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        itemList.forEach(item -> {
            HashMap<String, Object> map = new HashMap<>();
            map.put("name", item.getName());
            map.put("comment", item.getComment());
            map.put("weight", item.getWeight());
            map.put("score", item.getScore());
            mapList.add(map);
        });


        // 文件路径配置
        String tempDirPath = "d:/";
        String targetFilePath = tempDirPath + File.separator + "专家评审签字表.docx"; // 文件临时存放位置
        String wordTemplatePath = "d:/template.docx";

        // 导出模板和参数
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("policyName", "A政策");
        paramMap.put("code", "123456");
        paramMap.put("projectName", "大保健项目");
        paramMap.put("opinon", "很满意");
        paramMap.put("maplist", itemList);

        try {
            XWPFDocument doc = WordExportUtil.exportWord07(
                    wordTemplatePath, paramMap);
            FileOutputStream fos = new FileOutputStream(targetFilePath);
            doc.write(fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void importExcel() {
        ImportParams params = new ImportParams();
        params.setTitleRows(0);
        params.setHeadRows(1);
        //long start = new Date().getTime();

        List<Map<String, Object>> mapList = ExcelImportUtil.importExcel(
                new File("D:\\企业导入模板.xlsx"),
                Map.class, params);

        //System.out.println(new Date().getTime() - start);
        System.out.println(111);

    }


}












