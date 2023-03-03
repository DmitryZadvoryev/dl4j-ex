package ru.cbr;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataFirstImpl {

    {
        //tags = new LinkedHashMap<>(tagKeys.stream().collect(Collectors.toMap(k -> k, v -> false)));
        attributeValues = new LinkedHashMap<>(tagAttrKeys.stream().collect(Collectors.toMap(k -> k, v -> "")));
        //hasAttributes = new LinkedHashMap<>(hasTagAttrKeys.stream().collect(Collectors.toMap(k -> k, v -> false)));
    }

    //public static final List<String> hasTagAttrKeys = Arrays.asList("has_accept", "has_accept-charset", "has_accesskey", "has_action", "has_alt", "has_async", "has_autocomplete", "has_autofocus", "has_autoplay", "has_charset", "has_checked", "has_cite", "has_class", "has_cols", "has_colspan", "has_content", "has_contenteditable", "has_contextmenu", "has_controls", "has_coords", "has_data", "has_datetime", "has_default", "has_defer", "has_dir", "has_dirname", "has_disabled", "has_download", "has_draggable", "has_dropzone", "has_enctype", "has_for", "has_form", "has_formaction", "has_headers", "has_height", "has_hidden", "has_high", "has_href", "has_hreflang", "has_http-equiv", "has_id", "has_ismap", "has_kind", "has_label", "has_lang", "has_list", "has_loop", "has_low", "has_max", "has_maxlength", "has_media", "has_method", "has_min", "has_multiple", "has_muted", "has_name", "has_novalidate", "has_onabort", "has_onafterprint", "has_onbeforeprint", "has_onbeforeunload", "has_onblur", "has_oncanplay", "has_oncanplaythrough", "has_onchange", "has_onclick", "has_oncontextmenu", "has_oncopy", "has_oncuechange", "has_oncut", "has_ondblclick", "has_ondrag", "has_ondragend", "has_ondragenter", "has_ondragleave", "has_ondragover", "has_ondragstart", "has_ondrop", "has_ondurationchange", "has_onemptied", "has_onended", "has_onerror", "has_onfocus", "has_onhashchange", "has_oninput", "has_oninvalid", "has_onkeydown", "has_onkeypress", "has_onkeyup", "has_onload", "has_onloadeddata", "has_onloadedmetadata", "has_onloadstart", "has_onmousedown", "has_onmousemove", "has_onmouseout", "has_onmouseover", "has_onmouseup", "has_onmousewheel", "has_onoffline", "has_ononline", "has_onpageshow", "has_onpaste", "has_onpause", "has_onplay", "has_onplaying", "has_onprogress", "has_onratechange", "has_onreset", "has_onresize", "has_onscroll", "has_onsearch", "has_onseeked", "has_onseeking", "has_onselect", "has_onshow", "has_onstalled", "has_onsubmit", "has_onsuspend", "has_ontimeupdate", "has_ontoggle", "has_onunload", "has_onvolumechange", "has_onwaiting", "has_onwheel", "has_open", "has_optimum", "has_pattern", "has_placeholder", "has_poster", "has_preload", "has_readonly", "has_rel", "has_required", "has_reversed", "has_rows", "has_rowspan", "has_sandbox", "has_scope", "has_selected", "has_shape", "has_size", "has_sizes", "has_span", "has_spellcheck", "has_src", "has_srcdoc", "has_srclang", "has_srcset", "has_start", "has_step", "has_style", "has_tabindex", "has_target", "has_title", "has_translate", "has_type", "has_usemap", "has_value", "has_width", "has_wrap");
    public static final List<String> tagAttrKeys = Arrays.asList("accept", "accept-charset", "accesskey", "action", "alt", "async", "autocomplete", "autofocus", "autoplay", "charset", "checked", "cite", "class", "cols", "colspan", "content", "contenteditable", "contextmenu", "controls", "coords", "data", "datetime", "default", "defer", "dir", "dirname", "disabled", "download", "draggable", "dropzone", "enctype", "for", "form", "formaction", "headers", "height", "hidden", "high", "href", "hreflang", "http-equiv", "id", "ismap", "kind", "label", "lang", "list", "loop", "low", "max", "maxlength", "media", "method", "min", "multiple", "muted", "name", "novalidate", "onabort", "onafterprint", "onbeforeprint", "onbeforeunload", "onblur", "oncanplay", "oncanplaythrough", "onchange", "onclick", "oncontextmenu", "oncopy", "oncuechange", "oncut", "ondblclick", "ondrag", "ondragend", "ondragenter", "ondragleave", "ondragover", "ondragstart", "ondrop", "ondurationchange", "onemptied", "onended", "onerror", "onfocus", "onhashchange", "oninput", "oninvalid", "onkeydown", "onkeypress", "onkeyup", "onload", "onloadeddata", "onloadedmetadata", "onloadstart", "onmousedown", "onmousemove", "onmouseout", "onmouseover", "onmouseup", "onmousewheel", "onoffline", "ononline", "onpageshow", "onpaste", "onpause", "onplay", "onplaying", "onprogress", "onratechange", "onreset", "onresize", "onscroll", "onsearch", "onseeked", "onseeking", "onselect", "onshow", "onstalled", "onsubmit", "onsuspend", "ontimeupdate", "ontoggle", "onunload", "onvolumechange", "onwaiting", "onwheel", "open", "optimum", "pattern", "placeholder", "poster", "preload", "readonly", "rel", "required", "reversed", "rows", "rowspan", "sandbox", "scope", "selected", "shape", "size", "sizes", "span", "spellcheck", "src", "srcdoc", "srclang", "srcset", "start", "step", "style", "tabindex", "target", "title", "translate", "type", "usemap", "value", "width", "wrap");
    public static final List<String> tagKeys = Arrays.asList("tag", "html", "head", "title", "body", "h1", "h2", "h3", "h4", "h5", "h6", "p", "br", "hr", "acronym", "abbr", "address", "b", "bdi", "bdo", "big", "blockquote", "center", "cite", "code", "del", "dfn", "em", "font", "i", "ins", "kbd", "mark", "meter", "pre", "progress", "q", "rp", "rt", "ruby", "s", "samp", "small", "strike", "strong", "sub", "sup", "template", "time", "tt", "u", "var", "wbr", "form", "input", "textarea", "button", "select", "optgroup", "option", "label", "fieldset", "legend", "datalist", "output", "frame", "frameset", "noframes", "iframe", "img", "map", "area", "canvas", "figcaption", "figure", "picture", "svg", "audio", "source", "track", "video", "a", "link", "nav", "ul", "ol", "li", "dir", "dl", "dt", "dd", "menu", "menuitem", "table", "caption", "th", "tr", "td", "thead", "tbody", "tfoot", "col", "colgroup", "style", "div", "span", "header", "footer", "main", "section", "article", "aside", "details", "dialog", "summary", "data", "meta", "base", "basefont", "script", "noscript", "applet", "embed", "object", "param");

    //private Map<String, Boolean> tags;

    private String tag;

    private Map<String, String> attributeValues;

    //private Map<String, Boolean> hasAttributes;

    private String text;

    //private boolean haveText;

    private long heightOfElement, weightOfElement;

    private String fullCss;

    private long childCount;

    //Что делать с атрибутом data-*


    public DataFirstImpl(String text, long heightOfElement, long weightOfElement, String fullCss, long childCount) {
        this.text = text;
        //this.haveText = Objects.isNull(text);
        this.heightOfElement = heightOfElement;
        this.weightOfElement = weightOfElement;
        this.fullCss = fullCss;
        this.childCount = childCount;
    }

    public void addAttrValue(String key, String value) {
        attributeValues.put(key, value);
    }

    public String[] toArray() {
        //String[] tags = this.tags.values().stream().map(b -> Boolean.toString(b)).toArray(String[]::new);
        String[] attributes = this.attributeValues.values().toArray(String[]::new);
        return Stream.of(
                        new String[]{this.tag},
                        attributes,
                        new String[]{
                                this.text,
                                Long.toString(this.heightOfElement),
                                Long.toString(this.weightOfElement),
                                this.fullCss,
                                Long.toString(this.childCount)
        }
                )
                .flatMap(Stream::of)
                .toArray(String[]::new);
    }
}
