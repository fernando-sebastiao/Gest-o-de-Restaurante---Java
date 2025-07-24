package SwingComponents;
/**
 * @  Title:        GestaoCandidaturaMilitante
 * @  Version:
 * @  Copyright:    Copyright (c) 2003
 * @  Author:       Ruben Paxi Quissanga
 * @  Company:      ucan
 * @  Description:  Projecto final (Interaçao Homem Maquina
 * @                / Fundamentos de Programaçao III)
 */



public class Index_String extends Index_General
{
  String Key;

  public Index_String(int pos, String k)
  {
    super(pos);
    Key = k;
  }
  /*public String toString()
  {
    return "(" + super.toString() + ", " + Key + ")";
  }*/
  public String toString()
  {
    return "(" + super.get_Posicao() + ", " + Key + ")";
  }
  public String get_Key()
  {
    return Key;
  }
  /*public int get_Posicao()
  { return Integer.parseInt( super.toString()); } */
} 