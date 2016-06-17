using System;
using System.Threading;
using Microsoft.Owin.Hosting;
using System.Configuration;

namespace Poc.Docker.OrderApi
{
    internal class Program
    {
        private static readonly string BaseAddress = ConfigurationManager.AppSettings.Get("address");

        private static void Main(string[] args)
        {
            using (WebApp.Start<Startup>(url: BaseAddress))
            {
                Console.WriteLine("Application started...");
                var readVal = Console.ReadLine();
                while (string.IsNullOrEmpty(readVal))
                {
                    Thread.Sleep(5000);
                    readVal = Console.ReadLine();
                }
            }
        }
    }
}
