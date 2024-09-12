package exception;

public class LimitLoginException extends Exception{
	private int contador;
	public LimitLoginException(String message, int contador) {
		this.contador = contador;
	}
	public String toString() {
		return "Has superado el numero maximo de " + contador + " intentos";
	}
}
