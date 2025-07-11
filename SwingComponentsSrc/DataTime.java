package SwingComponents;
import java.text.DecimalFormat;
import java.util.*;
import java.io.*;

public class DataTime extends Data {

  private int hora, minuto, segundo;

  public DataTime() {

    // chama o construtor sem argumentos da classe Data()

    Date d = new Date();
    Calendar c = Calendar.getInstance();
    c.setTime(d);

    int h = c.get(Calendar.HOUR_OF_DAY);
    int m = c.get(Calendar.MINUTE);
    int s = c.get(Calendar.SECOND);

    // preciso fazer o setData: consistencia
    setData(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DATE));
    setTime(h, m, s);
  }

  public DataTime(DataTime dt)
  {
    hora = dt.hora;
    minuto = dt.minuto;
    segundo = dt.segundo;
  }

  public DataTime(int seg)
  {
    setTime(seg);
  }

  public DataTime( int h, int m, int s)
  {
    setTime(h, m, s);
  }

  public DataTime(String data, int seg) throws Exception
  {
    super(data);
    setTime(seg);
  }

  public DataTime(String data, int h, int m, int s) throws Exception
  {
    super(data);
    setTime(h, m, s);
  }

  public DataTime(int d, int m, int a, int seg)
  {
    super(d, m, a);
    setTime(seg);
  }

  public DataTime(int d, int m, int a, int h, int min, int s)
  {
    super(d, m, a);
    setTime(h, min, s);
  }

  public void setTime(int seg)
  {
    int h = seg/3600;
    int m = (seg%3600)/60;
    int s = seg%60;

    setTime(h, m, s);
  }

  public void setTime(int h, int m, int s)
  {
    hora = (h >= 0 && h < 24) ? h : 0;
    minuto = (m >= 0 && m < 60) ? m : 0;
    segundo = (s >= 0 && s < 60) ? s : 0;
  }

  public void tick()
  {
    if (++segundo > 59)
    {
      segundo = 0;
      if (++minuto > 59)
      {
        minuto = 0;
        if (++hora > 23)
        {
          hora = 0;
          proximoDia();
        }
      }
    }
  }

  public String toString()
  {
    DecimalFormat f = new DecimalFormat("00");

    return super.toString()
        + " " + f.format(hora) + ":" + f.format(minuto) + ":" + f.format(segundo);
  }

  public boolean save(RandomAccessFile file) throws IOException
  {
    // escrever primeiro a data
    if (!super.save(file))
      return false;

    // escrever depois o tempo
    file.writeInt(hora);
    file.writeInt(minuto);
    file.writeInt(segundo);

    return true;
  }

  public boolean read(RandomAccessFile file) throws IOException
  {
    // ler primeiro a data
    if (!super.read(file))
      return false;

    // depois o tempo
    int h = file.readInt();
    int m = file.readInt();
    int s = file.readInt();

    setTime(h, m, s);

    return true;
  }

  public int size()
  {
    return super.size() + 12;
  }
}
