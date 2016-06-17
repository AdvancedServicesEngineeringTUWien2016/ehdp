using System.Configuration;
using System.Net;
using System.Net.Mail;
using System.Text;
using Poc.Docker.OrderApi.Model;

namespace Poc.Docker.OrderApi.Mailer
{
    public class NotificationMailer
    {
        private static readonly string Sender = ConfigurationManager.AppSettings.Get("smtpsender");
        private static readonly string Url = ConfigurationManager.AppSettings.Get("smtpserver");
        private static readonly string User = ConfigurationManager.AppSettings.Get("smtpuser");
        private static readonly string Passwd = ConfigurationManager.AppSettings.Get("smtppass");
        private static readonly int Port = int.Parse(ConfigurationManager.AppSettings.Get("smtpport"));

        public static void SendNotification(Notification notification)
        {
            var client = new SmtpClient(Url, Port);
            var credentials = new NetworkCredential(User, Passwd);
            client.Credentials = credentials;
            client.EnableSsl = true;

            var mail = new MailMessage
            {
                Subject = notification.Subject,
                Body = notification.Message,
                From = new MailAddress(Sender),
                BodyEncoding = Encoding.UTF8,
                IsBodyHtml = true
            };
            mail.To.Add(new MailAddress(notification.Receiver));

            client.Send(mail);
        }
    }
}