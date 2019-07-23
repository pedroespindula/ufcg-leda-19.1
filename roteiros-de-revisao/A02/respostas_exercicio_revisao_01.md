### QUESTÃO 1 
Seguem as definições pedidas:
- **Overload**: Métodos com mesmo nome mas que recebem quantidades de parametros diferentes ou tipos de parametros diferentes. Com isso eles conseguem realizar uma operação com uma mesma lógica recenbendo varios tipos de parametros. 
```java
public int metodo(int i) {
	return i;
}

public int metodo(int i1, int i2) {
	return i1 + i2;
}
```
- **Override**: Método que sobrescreve outro metodo definido por uma classe pai, ou seja, usado para substituir um método de uma classe pai por outro. No entanto, o método à sobrescrever deve possuir o mesmo nome do metodo a ser sobreescrito.

```java
@Override
public String toString() {
	return "Este é um método de Override";
}
```

---

### QUESTÃO 2 
Como falado na questão anterior, o `Override` deve ser usado para substituir um método que foi implementado por uma classe pai. Um exemplo de quando isso pode ser usado é o método `toString` que é implementado por `Object`, possibilitando assim, o desenvolvedor personalizar a string que representará o objeto da classe criada.

```java
public class A {
	public int metodo(int i) {
		return i;
	}
}

public class B extends A {
	@Override int metodo(int i) {
		return i + 1;
	}
}
```

---

### QUESTÃO 3 
**Classe abstrata**: São classes que são usadas como modelos para evitar repetição de código. Elas fazem isso implementando metodos padrões. Além disso, elas podem definir metodos abstratos que deverão ser definidos por suas classes filhas. Por causa desses métodos abstratos, essas classes não podem ser instanciadas. Normalmente, essas classes abstratas usam esses métodos abstratos em suas implementações de métodos. 
```java

public abstract class A {
	public int retornaNum(int i) {
		return i;
	}

	public abstract String retornaString(String s);
}
```
**Situação de uso**: Duas classes que possuem metodos em comum e apenas a implementação de alguns métodos muda, podem ser redefinidos em uma classe abstrata pai que possui os metodos que possuem implementação comum e definem os métodos que devem ser implementados como abstratos. Assim, duas classes filhas extendem essa classe abstrata e implementa os métodos que são abstratos.

```java

public abstract class A {

	public int numMaisUm(int i) {
		return i + 1;
	}

	public abstract int numMultiplicado(int i);
}

public class B extends A {
	
	public int numMultiplicado(int i) {
		return i * 2;
	}

}

public class C extends A {
	
	public int numMultiplicado(int i) {
		return i * 3;
	}

}
```

---

### QUESTÃO 4 
**Cast**: Mudança de referencia de um objeto.
**Uso de cast**: Se um objeto for de uma classe filha mas tem como referencia uma classe pai e for necessario usar a referencia da classe filha, usa-se o cast
```java

public class A {
	public int metodoA() {
		return 1;
	}

}

public class B extends A {

	public int metodoB() {
		return 2;
	}

}


public class Main {

	public static void main (String[] args) {

		B b = new B();

		b.metodoA();
		b.metodoB();

		A a = (A)b;

		a.metodoA();
		// a.metodoB();

	}


}

I1 : x

c1 - I1 : y

I1[]


I1 a = I1[2]

a.y()

((c1) a).y()

```
