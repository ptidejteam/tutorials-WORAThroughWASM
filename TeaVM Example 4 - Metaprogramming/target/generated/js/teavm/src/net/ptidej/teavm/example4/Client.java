// From https://www.baeldung.com/java-teavm
package net.ptidej.teavm.example4;

import org.teavm.jso.dom.html.HTMLDocument;
import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.metaprogramming.Meta;
import org.teavm.metaprogramming.Metaprogramming;
import org.teavm.metaprogramming.ReflectClass;
import org.teavm.metaprogramming.Value;
import org.teavm.metaprogramming.reflect.ReflectField;

class A {
	private final String foo;

	A(final String foo) {
		this.foo = foo;
	}

	String getFoo() {
		return this.foo;
	}
}

class B {
}

public class Client {
	public static Object getFoo(final Object obj) {
		return Client.getFooImpl(obj.getClass(), obj);
	}

	@Meta
	private static native Object getFooImpl(final Class<?> cls,
			final Object obj);

	private static void getFooImpl(final ReflectClass<Object> cls,
			final Value<Object> obj) {
		final ReflectField field = cls.getField("foo");
		if (field != null) {
			Metaprogramming.exit(() -> field.get(obj));
		}
		else {
			Metaprogramming.exit(() -> null);
		}
	}

	public static void main(final String[] args) {
		final HTMLDocument document = HTMLDocument.current();

		final HTMLElement h1 = document.createElement("h1");
		h1.appendChild(document.createTextNode("TeaVM Example"));
		document.getBody().appendChild(h1);

		final HTMLElement resultDiv = document.createElement("div");
		document.getBody().appendChild(resultDiv);

		resultDiv.setTextContent("Client.getFoo(new A(\"barbaz\")): "
				+ Client.getFoo(new A("barbaz"))
				+ "<br>Client.getFoo(new B()): " + Client.getFoo(new B()));
	}
}