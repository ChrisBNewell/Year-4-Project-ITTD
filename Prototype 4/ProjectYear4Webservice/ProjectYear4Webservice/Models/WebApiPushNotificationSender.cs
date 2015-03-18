using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Microsoft.ServiceBus.Notifications;
using System.Threading.Tasks;


namespace ProjectYear4Webservice.Models
{
    public class WebApiPushNotificationSender
    {
        public static async Task SendNotificationAsync(String pushMessage)
        {
            NotificationHubClient hub = NotificationHubClient.CreateClientFromConnectionString("Endpoint=sb://year4projectnotificationhub-ns.servicebus.windows.net/;SharedAccessKeyName=DefaultFullSharedAccessSignature;SharedAccessKey=citUP3C8r065ML60SNvJy7V/RkLmrKlvluC3JMNTEqI=", "year4projectnotificationhub");
            //await hub.SendGcmNativeNotificationAsync("{ \"data\" : {\"msg\":\"Hello from Azure!\"}}");
            await hub.SendGcmNativeNotificationAsync("{ \"data\" : {\"msg\":\"" + pushMessage + "\"}}");
        }
    }
}