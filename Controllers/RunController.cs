using Microsoft.AspNetCore.Mvc;

namespace FitnessApp.Controllers
{
    public class RunController : Controller
    {
        public IActionResult Index()
        {
            return View();
        }
    }
}
