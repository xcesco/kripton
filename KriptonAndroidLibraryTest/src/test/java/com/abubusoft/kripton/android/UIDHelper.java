package com.abubusoft.kripton.android;
/**
 * 
 */


import java.util.regex.Pattern;

/**
 * <p>
 * Uno UID è una stringa che rappresenta un long in base 32 più 3 caratteri
 * messi in append come flag. <b>La lunghezza in caratteri è sempre e comunque 16!</b>
 * </p>
 * 
 * <p>
 * I flag servono allo stato attuale per definire una priorità tra gli uid in
 * fase di ordinamento. Ci sono gli uid a priorità normale, che hanno dei flag
 * '000' e quelli a priorità bassa che hanno i flag uguali a 'www'. Da notare
 * che 'w' è un carattere che non viene utilizzato nella codifica a base 32.
 * </p>
 * 
 * <p>
 * Il range del numero che può contenere varia da 0 a {@link #MAX_VALUE}
 * 
 * @author Cesco
 * 
 */
public abstract class UIDHelper {

	private static String PATTERN = "([0w]{3})([\\da-v]{13})";

	/**
	 * maschera da usare per definire uno uid.
	 */
	private static final String PAD = "0000000000000000";

	/**
	 * <p>
	 * Valore numerico massimo ammissibile per uno UID,
	 * <code>9.2 * 10 ^ 18</code>, ovvero:
	 * </p>
	 * 
	 * <pre>
	 * 9 200 000 000 000 000 000
	 * </pre>
	 * 
	 */
	public static long MAX_VALUE = 9200000000000000000L;

	/**
	 * Flags per indicare priorità normale dello UID
	 */
	private static final String PRIORITY_NORMAL = "000";

	/**
	 * Flags per indicare priorità bassa dello UID
	 */
	private static final String PRIORITY_LOW = "www";

	/**
	 * numero di caratteri destinati a contenere i flag in append
	 */
	private static final int FLAG_SIZE = PRIORITY_LOW.length();

	/**
	 * base numerica usata per la conversione da long a sua rappresentazione a
	 * stringa
	 */
	private static final int NUMERIC_BASE = 32;

	/**
	 * <p>
	 * Genera uno uid a partire da un long che può andare da 0 a
	 * {@link #MAX_VALUE}
	 * </p>
	 * 
	 * @param id
	 *            id da usare
	 * @return uid
	 */
	public static final String generateUID(long id) {
		return generateUID(id, true);
	}
	
	/**
	 * genera uno uid a partire dal current time mills.
	 * 
	 * @return
	 */
	public static final String generateUID()
	{
		return generateUID(System.currentTimeMillis());
	}
	
	/**
	 * genera uno uid a partire dal current mill con una priorità bassa, ovvero con priority low.
	 * 
	 * si ricorda che gli uid con priorità low sono quelli che vanno sempre in fondo a qualunque uid
	 * di tipo normal
	 * @return
	 */
	public static final String generateLowPriorityUID()
	{
		return generateUID(System.currentTimeMillis(), false);
	}

	/**
	 * <p>
	 * Genera uno uid a partire da un long che può andare da 0 a
	 * {@link #MAX_VALUE}
	 * </p>
	 * 
	 * @param id
	 * @param normal
	 *            indica se la priorità è normale o più bassa
	 * @return uid
	 */
	public static final String generateUID(long id, boolean normal) {
		String s0 = Long.toString(id, NUMERIC_BASE);
		String padded = s0;
		if (PAD.length() > s0.length()) {
			padded = PAD.substring(s0.length()) + s0;
		}

		// verifichiamo se inserire low priority
		if (!normal) {
			padded = PRIORITY_LOW + padded.substring(FLAG_SIZE);
		}

		return padded;
	}

	public static boolean hasFlag(String uid) {
		if (PAD.length() == uid.length()) {
			return uid.substring(0, FLAG_SIZE).compareTo(PRIORITY_NORMAL) != 0;
		} else {
			// ASSERT; non è un UID
			return false;
		}
	}

	/**
	 * Verifica che lo uid abbia una priorità normale.
	 * 
	 * @param uid
	 *            unique identifier
	 * @return <code>true</code> se la parte dello uid è uguale
	 *         {@link UIDHelper#PRIORITY_LOW}
	 */
	public static boolean isNormalPriority(String uid) {
		if (PAD.length() == uid.length()) {
			return uid.substring(0, FLAG_SIZE).compareTo(PRIORITY_NORMAL) == 0;
		} else {
			// ASSERT; non è un UID
			return false;
		}
	}

	/**
	 * Verifica che lo uid abbia una priorità bassa.
	 * 
	 * @param uid
	 * @return
	 */
	public static boolean isLowerPriority(String uid) {
		return !isNormalPriority(uid);
	}

	/**
	 * Dato uno uid lo decodifica.
	 * 
	 * @param uid
	 * @return
	 */
	public static long decodeUID(String uid) {
		if (UIDHelper.isValidUID(uid)) {
			// rimuoviamo i 3 char, dato che non ci servono mai!
			return Long.parseLong(uid.substring(FLAG_SIZE), NUMERIC_BASE);
		} else {
			return -1;
		}
	}

	/**
	 * <p>
	 * Se true indica che la stringa data in input è un UID sintatticamente
	 * valido.
	 * </p>
	 * 
	 * @param input
	 * @return
	 */
	public static boolean isValidUID(String input) {
		return Pattern.matches(PATTERN, input);
	}
}
