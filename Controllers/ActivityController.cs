using FitnessApp.Data;
using FitnessApp.Models;
using Microsoft.AspNetCore.Mvc;

namespace FitnessApp.Controllers
{
    public class ActivityController : Controller
    {
        private readonly ApplicationDbContext _context;

        public ActivityController(ApplicationDbContext context)
        {
            _context = context;
        }


        public async Task<IActionResult> Index()
        {
            var viewModel = new ActivityViewModel
            {
                Runs = _context.Runs.ToList(),
                Swims = _context.Swims.ToList(),
                Bikes = _context.Bikes.ToList()
            };
            return View(viewModel);
        }
    }
}
