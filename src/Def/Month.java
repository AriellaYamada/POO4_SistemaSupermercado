package Def;

import java.util.LinkedList;
import java.util.List;

public class Month {

	public static final List<String> months = new LinkedList<>();

	//Mapeia os meses por inteiros
	static void initializeMonthsList() {
		months.add(0, null);
		months.add(1, "Janeiro");
		months.add(2, "Fevereiro");
		months.add(3, "Março");
		months.add(4, "Abril");
		months.add(5, "Maio");
		months.add(6, "Junho");
		months.add(7, "Julho");
		months.add(8, "Agosto");
		months.add(9, "Setembro");
		months.add(10, "Outubro");
		months.add(11, "Novembro");
		months.add(12, "Dezembro");
	}

	//Transforma o mês de int para string
	public static String intToMonth (int n) {
		if (n < 1 || n > 12) return null;
		if (months.isEmpty()) initializeMonthsList();
		return months.get(n);
	}

	//Transforma o mês de strig para int
	public static int monthToInt (String m) {
		if (months.isEmpty()) initializeMonthsList();
		return months.indexOf(m);
	}
}
