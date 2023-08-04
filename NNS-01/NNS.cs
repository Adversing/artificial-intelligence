using System;
using System.Collections.Generic;
using System.Linq;

namespace NeuralNetworkSimulation
{
    class NNS
    {
        static void Main(string[] args)
        {
            // Set up the network
            int numNeurons = 100;
            int numHealthy = 1;
            int numIll = numNeurons - numHealthy;
            List<bool> neurons = new List<bool>();
            for (int i = 0; i < numHealthy; i++)
            {
                neurons.Add(true);
            }
            for (int i = 0; i < numIll; i++)
            {
                neurons.Add(false);
            }

            // Set up the simulation
            int time = 0;
            int spreadingSpeed = 1;
            bool allHealthy = false;

            // Run the simulation
            while (!allHealthy)
            {
                allHealthy = true;
                for (int i = 0; i < numNeurons; i++)
                {
                    if (!neurons[i])
                    {
                        allHealthy = false;
                        int numHealthyNeighbors = 0;
                        for (int j = Math.Max(0, i - spreadingSpeed); j <= Math.Min(numNeurons - 1, i + spreadingSpeed); j++)
                        {
                            if (neurons[j])
                            {
                                numHealthyNeighbors++;
                            }
                        }
                        if (numHealthyNeighbors > 0)
                        {
                            neurons[i] = true;
                            Console.WriteLine("Neuron " + i + " is now healthy.");
                        }
                    }
                }
                time++;
            }

            // Print the results
            Console.WriteLine("Simulation complete. It took " + time + " time units to cure all neurons.");
        }
    }
}
