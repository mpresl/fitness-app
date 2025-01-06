using FitnessApp.Data;
using FitnessApp.Models;
using Microsoft.AspNetCore.Mvc;

namespace FitnessApp.Controllers
{
    public class RunController : Controller
    {
        private readonly ApplicationDbContext _context;

        public RunController(ApplicationDbContext context)
        {
            _context = context;
        }

        [HttpGet]
        public IActionResult TrackRun()
        {
            return View();
        }

        [HttpPost]
        public async Task<IActionResult> TrackRun(Run run)
        {
            if (ModelState.IsValid)
            {
                _context.Add(run);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index), "Home");
            }
            return View(run);
        }
    }
}
