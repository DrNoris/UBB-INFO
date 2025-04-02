using System.Globalization;
using lab9.Domain;

namespace Service
{
    using Repository;

    public class ServiceNumarComplex
    {
        private readonly RepositoryNumarComplex _repositoryNumarComplex;

        public ServiceNumarComplex(RepositoryNumarComplex repositoryNumarComplex)
        {
            _repositoryNumarComplex = repositoryNumarComplex;
        }

        public void AddNumarComplex(double real, double imaginar)
        {
            _repositoryNumarComplex.CreateNumarComplex(real, imaginar);
            if (_repositoryNumarComplex.GetNumereComplexe().Count - _repositoryNumarComplex.GetOperations().Count > 1
                || _repositoryNumarComplex.GetOperations().Count > _repositoryNumarComplex.GetNumereComplexe().Count)
            {
                throw new ArgumentException("Invalid complex number format");
            }
        }

        public void AddOperator(string word)
        {
            switch (word)
            {
                case "+":
                    _repositoryNumarComplex.CreateOperator(Operation.ADDITION);
                    break;

                case "-":
                    _repositoryNumarComplex.CreateOperator(Operation.SUBSTRACTION);
                    break;

                case "*":
                    _repositoryNumarComplex.CreateOperator(Operation.MULTIPLICATION);
                    break;

                case "/":
                    _repositoryNumarComplex.CreateOperator(Operation.DIVISION);
                    break;

                default:
                    throw new ArgumentException("Invalid operator");
            }
        }

        public double[] ConvertComplexString(string complexStr)
        {
            // Remove whitespace
            complexStr = complexStr.Replace(" ", "");
            complexStr = complexStr.Replace(")", "");
            complexStr = complexStr.Replace("(", "");

            // Updated regex to account for complex number formats
            string regex = "([-+]?\\d*\\.?\\d*)?([-+]?\\d*\\.?\\d*\\*?i)?";
            var pattern = new System.Text.RegularExpressions.Regex(regex);
            var match = pattern.Match(complexStr);

            if (match.Success)
            {
                string realPart = !string.IsNullOrEmpty(match.Groups[1].Value) ? match.Groups[1].Value : "";
                string imaginaryPart = !string.IsNullOrEmpty(match.Groups[2].Value) ? match.Groups[2].Value : "";

                double imaginary = 0;
                double real = 0;

                if (!string.IsNullOrEmpty(realPart) && (imaginaryPart.Contains("*i") && imaginaryPart.Length < 3 || realPart == "-" || realPart == "*"))
                {
                    imaginaryPart = realPart + imaginaryPart;
                    realPart = "";
                }

                if (imaginaryPart.Contains("i"))
                {
                    string s = imaginaryPart.Contains("*") ? 
                        imaginaryPart.Replace("i", "").Replace("*", "") : 
                        imaginaryPart.Replace("i", "1");
                    imaginary = double.Parse(s, CultureInfo.InvariantCulture);
                }

                if (!string.IsNullOrEmpty(realPart))
                {
                    real = double.Parse(realPart, CultureInfo.InvariantCulture);
                }

                return new[] { real, imaginary };
            }
            else
            {
                throw new ArgumentException("Invalid complex number format");
            }
        }

        public List<NumarComplex> GetNumere()
        {
            return _repositoryNumarComplex.GetNumereComplexe();
        }

        public List<Operation> GetOperations()
        {
            return _repositoryNumarComplex.GetOperations();
        }
    }

    public class ExpressionFactory
    {
        private static ExpressionFactory _instance = null!;

        private ExpressionFactory() { }

        public static ExpressionFactory GetInstance()
        {
            if (_instance == null)
            {
                _instance = new ExpressionFactory();
            }
            return _instance;
        }

        public ComplexExpression CreateExpression(Operation operation, List<NumarComplex> args)
        {
            return operation switch
            {
                Operation.ADDITION => new AdditionExpression(operation, args),
                Operation.SUBSTRACTION => new SubstractionExpression(operation, args),
                Operation.MULTIPLICATION => new MultiplicationExpression(operation, args),
                Operation.DIVISION => new DivisionExpression(operation, args),
                _ => null,
            };
        }
    }
}
