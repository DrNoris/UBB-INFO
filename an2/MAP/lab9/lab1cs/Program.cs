using lab9.Domain;
using Repository;
using Service;

namespace MainProgram
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Expression: ");
            string input = Console.ReadLine();

            if (string.IsNullOrEmpty(input))
            {
                Console.WriteLine("No input provided.");
                return;
            }

            string[] words = input.Split(' ', StringSplitOptions.RemoveEmptyEntries);

            var repo = new RepositoryNumarComplex();
            var serv = new ServiceNumarComplex(repo);

            foreach (string word in words)
            {
                if (!"+-/*".Contains(word))
                {
                    try
                    {
                        double[] valori = serv.ConvertComplexString(word);
                        serv.AddNumarComplex(valori[0], valori[1]);
                    }
                    catch (ArgumentException e)
                    {
                        Console.WriteLine(e.Message);
                        return;
                    }
                }
                else
                {
                    if (!"+-/*".Contains(word))
                        Console.WriteLine("Invalid operator");
                    else
                        serv.AddOperator(word);
                }
            }

            var expressionFactory = ExpressionFactory.GetInstance();
            List<Operation> operations = serv.GetOperations();
            List<NumarComplex> numarComplexes = serv.GetNumere();

            while (operations.Count > 0)
            {
                int index = 0;
                for (int i = 0; i < operations.Count; ++i)
                {
                    if (operations[i] == Operation.MULTIPLICATION || operations[i] == Operation.DIVISION)
                    {
                        index = i;
                        break;
                    }
                }

                var numere = new List<NumarComplex>
                {
                    numarComplexes[index],
                    numarComplexes[index + 1]
                };

                var expression = expressionFactory.CreateExpression(operations[index], numere);
                NumarComplex rezultat = expression.Execute();

                operations.RemoveAt(index);
                numarComplexes.RemoveAt(index);
                numarComplexes.RemoveAt(index);
                numarComplexes.Insert(index, rezultat);
            }

            Console.WriteLine("Rezultatul este: " + numarComplexes[0]);
        }
    }
}
