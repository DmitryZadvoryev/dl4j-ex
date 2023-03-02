package ru.cbr;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Parser {

    public static List<DataFirstImpl> parse() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            Object o = ((JavascriptExecutor) WebDriverRunner.getWebDriver()).executeScript(
                    "function fullCssPath(el){\n" +
                            "  var names = [];\n" +
                            "  while (el.parentNode){\n" +
                            "    if (el.id){\n" +
                            "      names.unshift('#'+el.id);\n" +
                            "      break;\n" +
                            "    }else{\n" +
                            "      if (el==el.ownerDocument.documentElement) names.unshift(el.tagName);\n" +
                            "      else{\n" +
                            "        for (var c=1,e=el;e.previousElementSibling;e=e.previousElementSibling,c++);\n" +
                            "        names.unshift(el.tagName+\":nth-child(\"+c+\")\");\n" +
                            "      }\n" +
                            "      el=el.parentNode;\n" +
                            "    }\n" +
                            "  }\n" +
                            "  return names.join(\" > \");\n" +
                            "};" +
                            "let elements = Array.from(document.querySelectorAll(\"*\")).filter(e=>e.getBoundingClientRect().height > 0 && e.getBoundingClientRect().width > 0 && e.getAttribute('class') !== null && e.childElementCount <= 1 && e.getAttributeNames().length >=1);\n" +
                            "let result = [];\n" +
                            "   elements.forEach(e => result.push({\n" +
                            "   element : e, \n" +
                            "   attributeNames: e.getAttributeNames(), \n" +
                            "   text: e.innerText, \n" +
                            "   height: Math.round(e.getBoundingClientRect().height), \n" +
                            "   width: Math.round(e.getBoundingClientRect().width), \n" +
                            "   childElementCount: e.childElementCount, \n" +
                            "   fullCss: fullCssPath(e)}));\n" +
                            "   return result");

            List<DataFirstImpl> data = new ArrayList<>();

            for (int i = 0; i < ((ArrayList) o).size(); i++) {
                Map<String, Object> item = (Map<String, Object>) ((ArrayList) o).get(i);
                String text = (String) item.get("text");
                long height = (long) item.get("height");
                long width = (long) item.get("width");
                String fullCss = (String) item.get("fullCss");
                long childCount = (long) item.get("childElementCount");
                DataFirstImpl element = new DataFirstImpl(text, height, width, fullCss, childCount);
                ArrayList<String> attributeNames = (ArrayList<String>) item.get("attributeNames");
                WebElement webElement = (WebElement) item.get("element");
                attributeNames.forEach(a -> {
                    if (DataFirstImpl.tagAttrKeys.contains(a)) {
                        element.addAttrValue(a, ((String) ((JavascriptExecutor) WebDriverRunner.getWebDriver())
                                .executeScript("return arguments[0].getAttribute(arguments[1])", webElement, a)).toLowerCase().replaceAll("\"",""));
                    }
                });
                element.setTag(((String) ((JavascriptExecutor) WebDriverRunner.getWebDriver()).executeScript("return arguments[0].tagName", webElement)).toLowerCase());
                data.add(element);
            }
            return data;
        }
        throw new RuntimeException("Драйвер не запущен!");
    }
}
