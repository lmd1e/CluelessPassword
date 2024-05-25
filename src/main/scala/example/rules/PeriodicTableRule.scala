package periodictablerule

import rule._

class PeriodicTableRule extends  Rule{
  val periodicTable = Set("He", "Li", "Be","Ne", "Na", "Mg",
  "Al", "Si", "Cl", "Ar", "Ca", "Sc", "Ti", "Cr",
  "Mn", "Fe", "Co", "Ni", "Cu", "Zn", "Ga", "Ge", "As", "Se", "Br", "Kr",
  "Rb", "Sr", "Zr", "Nb", "Mo", "Tc", "Ru", "Rh", "Pd", "Ag", "Cd",
  "In", "Sn", "Sb", "Te", "Xe", "Cs", "Ba", "La", "Ce", "Pr", "Nd",
  "Pm", "Sm", "Eu", "Gd", "Tb", "Dy", "Ho", "Er", "Tm", "Yb", "Lu", "Hf",
  "Ta", "Re", "Os", "Ir", "Pt", "Au", "Hg", "Tl", "Pb", "Bi", "Po",
  "At", "Rn", "Fr", "Ra", "Ac", "Th", "Pa", "Np", "Pu", "Am", "Cm",
  "Bk", "Cf", "Es", "Fm", "Md", "No", "Lr")
  override def message(): String = "Your password must include a two letter \u001B[4msymbol from the periodic table\u001B[24m."
  override def check(password: String): Boolean = periodicTable.exists(password.contains)
}