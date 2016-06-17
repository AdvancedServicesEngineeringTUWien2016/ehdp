using System.Web.Http;
using Poc.Docker.OrderApi.Mailer;
using Poc.Docker.OrderApi.Model;

namespace Poc.Docker.OrderApi.Api
{
    public class MailerController : ApiController
    {
        [HttpPost]
        public IHttpActionResult Post(Notification notification)
        {
            NotificationMailer.SendNotification(notification);
            return Ok();
        }
    }
}
