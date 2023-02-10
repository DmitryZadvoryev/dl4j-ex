# dl4j-ex

//v1
let elements = Array.from(document.querySelectorAll("*"));
elements.filter(e=>e.getBoundingClientRect().height > 0 && e.getBoundingClientRect().width > 0 && e.getAttribute('class') !== null && e.childElementCount <= 1);
//v2
let elements = Array.from(document.querySelectorAll("*"));
elements.filter(e=>e.getBoundingClientRect().height > 0 && e.getBoundingClientRect().width > 0 && e.getAttribute('class') !== null && e.childElementCount <= 1 && e.getAttributeNames().length >=1    );
