package net.ptidej.teavm.example1;

import org.teavm.jso.dom.html.HTMLDocument;

public class Client {
    public static void main(String[] args) {
        var document = HTMLDocument.current();
        var div = document.createElement("div");
        div.appendChild(document.createTextNode("This is a DIV element created from Java code!"));
        document.getBody().appendChild(div);
    }
}
