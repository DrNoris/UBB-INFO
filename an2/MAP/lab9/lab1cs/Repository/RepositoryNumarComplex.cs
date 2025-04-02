using lab9.Domain;

namespace Repository
{
    public class RepositoryNumarComplex
    {
        private List<NumarComplex> NumereComplexe;
        private List<Operation> Operations;

        public RepositoryNumarComplex()
        {
            this.Operations = new List<Operation>();
            this.NumereComplexe = new List<NumarComplex>();
        }

        public void CreateNumarComplex(double real, double imaginar)
        {
            NumarComplex numarComplex = new NumarComplex(real, imaginar);
            NumereComplexe.Add(numarComplex);
        }

        public void CreateOperator(Operation operation)
        {
            Operations.Add(operation);
        }

        public List<NumarComplex> GetNumereComplexe()
        {
            return NumereComplexe;
        }

        public List<Operation> GetOperations()
        {
            return Operations;
        }
    }
}