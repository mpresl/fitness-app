using FitnessApp.Data;
using FitnessApp.Models;
using Microsoft.AspNetCore.Mvc;

namespace FitnessApp.Controllers
{
    public class SwimController : Controller
    {
        private readonly ApplicationDbContext _context;

        public SwimController(ApplicationDbContext context)
        {
            _context = context;
        }

        [HttpGet]
        public IActionResult TrackSwim()
        {
            return View();
        }

        [HttpPost]
        public async Task<IActionResult> TrackSwim(Swim swim)
        {
            if (ModelState.IsValid)
            {
                _context.Add(swim);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index), "Home");
            }
            return View(swim);
        }
    }
}
