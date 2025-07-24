package SwingComponents;

import java.util.*;
import java.text.*;
//import aplicacao.IRegisto;
import java.io.*;

public class Data //implements IRegisto 
{

  private int dia, mes, ano;

  private static int dias[] = {
      31, 28, 31,
      30, 31, 30,
      31, 31, 30,
      31, 30, 31
  };

  public static String meses[] = {
      "Jan", "Fev", "Mar",
      "Abr", "Mai", "Jun",
      "Jul", "Ago", "Set",
      "Out", "Nov", "Dez"
  };

  public Data() {
    //this(1, 1, 1970);
    Date d = new Date();
    Calendar c = Calendar.getInstance();
    c.setTime(d);

    int dia = c.get(Calendar.DATE);
    int mes = c.get(Calendar.MONTH) + 1;
    int ano = c.get(Calendar.YEAR);

    setData(dia, mes, ano);
  }

  // formato "dd mm aaaa"
  public Data(String data) throws Exception
  {
    StringTokenizer st = new StringTokenizer(data, "/- ");

    int dia = Integer.parseInt( st.nextToken());
    int mes = Integer.parseInt( st.nextToken());
    int ano = Integer.parseInt( st.nextToken());

    setData(dia, mes, ano);
  }

  public Data(Data dt)
  {
    setData(dt.dia, dt.mes, dt.ano);
  }

  public Data(int d, int m, int a)
  {
    setData(d, m, a);
  }

  public Data(int diasAno, int a)
  {
    int d = diasAno;
    int m = 1;

    setAno(a);

    for (int i = 0; i < 12; i++)
    {
      diasAno -= dias[i];

      if (diasAno <= 0)
      {
        m = i + 1;
        break;
      }

      d = diasAno;
    }

    setMes(m);
    setDia(d);
  }

  public Data(String mm, int d, int a)
  {
    int m;

    for (m = 1; m <= 12; m++)
      if (mm.equalsIgnoreCase(meses[m-1]))
        break;

    setAno(a);
    setMes(m);
    setDia(d);
  }

  public void setData(int d, int m, int a)
  {
    setAno(a);
    setMes(m);
    setDia(d);
  }

  public void proximoDia()
  {
    if(++dia > diasMes())
    {
      dia = 1;
      if (++mes > 12)
      {
        mes = 1;
        ano++;
      }
    }
  }

  // o set do dia deve fazer-se sempre depois do set do mes e do ano
  public void setDia(int d)
  {
    dia = (d >= 1 && d <= diasMes()) ? d : 1;
  }

  public void setMes(int m)
  {
    mes = (m >= 1 && m <= 12) ? m : 1;
    // dia = 1;
  }

  public void setAno(int a)
  {
    ano = (a >= 1) ? a : 1;
    // mes = 1;
    // dia = 1;
  }

  public int getDia()
  {
    return dia;
  }

  public int getMes()
  {
    return mes;
  }

  public int getAno()
  {
    return ano;
  }

  public int diasMes()
  {
    return (dias[mes-1] + ((bixesto() && mes == 2) ? 1 : 0));
  }

  private boolean bixesto()
  {
    return ((ano%400 == 0) || ((ano%4 == 0) && (ano%100 != 0)));
  }

  private int getDiasAno()
  {
    int diasAno = dia;

    for ( int m = 1; m < mes; m++)
      diasAno += dias[m-1] + ((m == 2 && bixesto()) ? 1 : 0);

    return diasAno;
  }

  public String toString()
  {
    DecimalFormat df = new DecimalFormat("00");
    DecimalFormat fAno = new DecimalFormat("0000");

    return df.format(dia) + "-" + df.format(mes) + "-" + fAno.format(ano);
  }

  public String toString(String formato)
  {
    if (formato.equalsIgnoreCase("ddd aaaa"))
    {
      DecimalFormat df = new DecimalFormat("000");
      DecimalFormat fAno = new DecimalFormat("0000");

      return df.format(getDiasAno()) + " " + fAno.format(ano);
    }
    else if (formato.equalsIgnoreCase("mm, dd aaaa"))
    {
      DecimalFormat fDia = new DecimalFormat("00");
      DecimalFormat fAno = new DecimalFormat("0000");

      return meses[mes-1] + ", " + fDia.format(dia) + " " + fAno.format(ano);
    }
    else // "dd mm aaaa"
      return toString();
  }

  public boolean save(RandomAccessFile file) throws IOException
  {
    if (file == null)
      return false;

    file.writeInt(dia);
    file.writeInt(mes);
    file.writeInt(ano);

    return true;
  }

  public boolean read(RandomAccessFile file) throws IOException
  {
    if (file == null)
      return false;

    int d = file.readInt();
    int m = file.readInt();
    int a = file.readInt();

    setData(d, m, a);

    return true;
  }

  public int size()
  {
    return 12;
  }
}
