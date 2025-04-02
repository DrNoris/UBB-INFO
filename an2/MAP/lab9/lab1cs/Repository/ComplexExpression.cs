using lab9.Domain;

namespace Repository
{
    public abstract class ComplexExpression
    {
        protected Operation Operation { get; set; }
        protected List<NumarComplex> NumereComplexe { get; set; }

        protected ComplexExpression(Operation operation, List<NumarComplex> numereComplexe)
        {
            Operation = operation;
            NumereComplexe = numereComplexe;
        }

        public NumarComplex Execute()
        {
            return ExecuteOneOperation();
        }

        public abstract NumarComplex ExecuteOneOperation();
    }
    
    public class AdditionExpression : ComplexExpression
    {
        public AdditionExpression(Operation operation, List<NumarComplex> numereComplexe) : base(operation, numereComplexe)
        {
        }

        public override NumarComplex ExecuteOneOperation()
        {
            NumarComplex nr1 = NumereComplexe[0];
            NumarComplex nr2 = NumereComplexe[1];

            return NumarComplex.adunare(nr1, nr2);
        }
    }
    
    public class SubstractionExpression : ComplexExpression
    {
        public SubstractionExpression(Operation operation, List<NumarComplex> numereComplexe) : base(operation, numereComplexe)
        {
        }

        public override NumarComplex ExecuteOneOperation()
        {
            NumarComplex nr1 = NumereComplexe[0];
            NumarComplex nr2 = NumereComplexe[1];

            return NumarComplex.scadere(nr1, nr2);
        }
    }
    
    public class MultiplicationExpression : ComplexExpression
    {
        public MultiplicationExpression(Operation operation, List<NumarComplex> numereComplexe) : base(operation, numereComplexe)
        {
        }

        public override NumarComplex ExecuteOneOperation()
        {
            NumarComplex nr1 = NumereComplexe[0];
            NumarComplex nr2 = NumereComplexe[1];

            return NumarComplex.inmultire(nr1, nr2);
        }
    }
    
    public class DivisionExpression : ComplexExpression
    {
        public DivisionExpression(Operation operation, List<NumarComplex> numereComplexe) : base(operation, numereComplexe)
        {
        }

        public override NumarComplex ExecuteOneOperation()
        {
            NumarComplex nr1 = NumereComplexe[0];
            NumarComplex nr2 = NumereComplexe[1];

            return NumarComplex.impartire(nr1, nr2);
        }
    }
}