package ru.cbr;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CD {
        public static void main(String[] args) {

            /*String text = "accept " +
                    "accept-charset " +
                    "accesskey " +
                    "action " +
                    "alt " +
                    "async " +
                    "autocomplete " +
                    "autofocus " +
                    "autoplay " +
                    "charset " +
                    "checked " +
                    "cite " +
                    "class " +
                    "cols " +
                    "colspan " +
                    "content " +
                    "contenteditable " +
                    "contextmenu " +
                    "controls " +
                    "coords " +
                    "data " +
                    "data-* " +
                    "datetime " +
                    "default " +
                    "defer " +
                    "dir " +
                    "dirname " +
                    "disabled " +
                    "download " +
                    "draggable " +
                    "dropzone " +
                    "enctype " +
                    "for " +
                    "form " +
                    "formaction " +
                    "headers " +
                    "height " +
                    "hidden " +
                    "high " +
                    "href " +
                    "hreflang " +
                    "http-equiv " +
                    "id " +
                    "ismap " +
                    "kind " +
                    "label " +
                    "lang " +
                    "list " +
                    "loop " +
                    "low " +
                    "max " +
                    "maxlength " +
                    "media " +
                    "method " +
                    "min " +
                    "multiple " +
                    "muted " +
                    "name " +
                    "novalidate " +
                    "onabort " +
                    "onafterprint " +
                    "onbeforeprint " +
                    "onbeforeunload " +
                    "onblur " +
                    "oncanplay " +
                    "oncanplaythrough " +
                    "onchange " +
                    "onclick " +
                    "oncontextmenu " +
                    "oncopy " +
                    "oncuechange " +
                    "oncut " +
                    "ondblclick " +
                    "ondrag " +
                    "ondragend " +
                    "ondragenter " +
                    "ondragleave " +
                    "ondragover " +
                    "ondragstart " +
                    "ondrop " +
                    "ondurationchange " +
                    "onemptied " +
                    "onended " +
                    "onerror " +
                    "onfocus " +
                    "onhashchange " +
                    "oninput " +
                    "oninvalid " +
                    "onkeydown " +
                    "onkeypress " +
                    "onkeyup " +
                    "onload " +
                    "onloadeddata " +
                    "onloadedmetadata " +
                    "onloadstart " +
                    "onmousedown " +
                    "onmousemove " +
                    "onmouseout " +
                    "onmouseover " +
                    "onmouseup " +
                    "onmousewheel " +
                    "onoffline " +
                    "ononline " +
                    "onpageshow " +
                    "onpaste " +
                    "onpause " +
                    "onplay " +
                    "onplaying " +
                    "onprogress " +
                    "onratechange " +
                    "onreset " +
                    "onresize " +
                    "onscroll " +
                    "onsearch " +
                    "onseeked " +
                    "onseeking " +
                    "onselect " +
                    "onshow " +
                    "onstalled " +
                    "onsubmit " +
                    "onsuspend " +
                    "ontimeupdate " +
                    "ontoggle " +
                    "onunload " +
                    "onvolumechange " +
                    "onwaiting " +
                    "onwheel " +
                    "open " +
                    "optimum " +
                    "pattern " +
                    "placeholder " +
                    "poster " +
                    "preload " +
                    "readonly " +
                    "rel " +
                    "required " +
                    "reversed " +
                    "rows " +
                    "rowspan " +
                    "sandbox " +
                    "scope " +
                    "selected " +
                    "shape " +
                    "size " +
                    "sizes " +
                    "span " +
                    "spellcheck " +
                    "src " +
                    "srcdoc " +
                    "srclang " +
                    "srcset " +
                    "start " +
                    "step " +
                    "style " +
                    "tabindex " +
                    "target " +
                    "title " +
                    "translate " +
                    "type " +
                    "usemap " +
                    "value " +
                    "width " +
                    "wrap";
            String[] s = text.split(" ");
            System.err.println(s.length);
            String collect = Arrays.stream(s).map(e -> "haveTagAttr" + e.substring(0, 1).toUpperCase() + e.substring(1)).collect(Collectors.joining(", "));
            System.out.print(collect);*/

            /*String text = "html " +
                    "head " +
                    "title " +
                    "body " +
                    "h1 " +
                    "h2 " +
                    "h3 " +
                    "h4 " +
                    "h5 " +
                    "h6 " +
                    "p " +
                    "br " +
                    "hr " +
                    "acronym " +
                    "abbr " +
                    "address " +
                    "b " +
                    "bdi " +
                    "bdo " +
                    "big " +
                    "blockquote " +
                    "center " +
                    "cite " +
                    "code " +
                    "del " +
                    "dfn " +
                    "em " +
                    "font " +
                    "i " +
                    "ins " +
                    "kbd " +
                    "mark " +
                    "meter " +
                    "pre " +
                    "progress " +
                    "q " +
                    "rp " +
                    "rt " +
                    "ruby " +
                    "s " +
                    "samp " +
                    "small " +
                    "strike " +
                    "strong " +
                    "sub " +
                    "sup " +
                    "template " +
                    "time " +
                    "tt " +
                    "u " +
                    "var " +
                    "wbr " +
                    "form " +
                    "input " +
                    "textarea " +
                    "button " +
                    "select " +
                    "optgroup " +
                    "option " +
                    "label " +
                    "fieldset " +
                    "legend " +
                    "datalist " +
                    "output " +
                    "frame " +
                    "frameset " +
                    "noframes " +
                    "iframe " +
                    "img " +
                    "map " +
                    "area " +
                    "canvas " +
                    "figcaption " +
                    "figure " +
                    "picture " +
                    "svg " +
                    "audio " +
                    "source " +
                    "track " +
                    "video " +
                    "a " +
                    "link " +
                    "nav " +
                    "ul " +
                    "ol " +
                    "li " +
                    "dir " +
                    "dl " +
                    "dt " +
                    "dd " +
                    "menu " +
                    "menuitem " +
                    "table " +
                    "caption " +
                    "th " +
                    "tr " +
                    "td " +
                    "thead " +
                    "tbody " +
                    "tfoot " +
                    "col " +
                    "colgroup " +
                    "style " +
                    "div " +
                    "span " +
                    "header " +
                    "footer " +
                    "main " +
                    "section " +
                    "article " +
                    "aside " +
                    "details " +
                    "dialog " +
                    "summary " +
                    "data " +
                    "head " +
                    "meta " +
                    "base " +
                    "basefont " +
                    "script " +
                    "noscript " +
                    "applet " +
                    "embed " +
                    "object " +
                    "param";

            String[] s = text.split(" ");
            System.err.println(s.length);
            String collect = Arrays.stream(s).map(e -> "is" + e.substring(0, 1).toUpperCase() + e.substring(1)).collect(Collectors.joining(", "));
            System.out.print(collect);*/

            String text = "accesskey " +
                    "class " +
                    "contenteditable " +
                    "contextmenu " +
                    "data-* " +
                    "dir " +
                    "draggable " +
                    "dropzone " +
                    "hidden " +
                    "id " +
                    "lang " +
                    "spellcheck " +
                    "style " +
                    "tabindex " +
                    "title " +
                    "translate";
            String[] s = text.split(" ");
            System.err.println(s.length);
            String collect = Arrays.stream(s).map(e -> "haveGlobalAttr" + e.substring(0, 1).toUpperCase() + e.substring(1)).collect(Collectors.joining(", "));
            System.out.print(collect);
        }


}
