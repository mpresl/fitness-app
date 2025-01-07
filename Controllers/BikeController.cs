using FitnessApp.Data;
using FitnessApp.Models;
using Microsoft.AspNetCore.Mvc;

namespace FitnessApp.Controllers
{
    public class BikeController : Controller
    {
        private readonly ApplicationDbContext _context;

        public BikeController(ApplicationDbContext context)
        {
            _context = context;
        }

        [HttpGet]
        public IActionResult TrackBike()
        {
            return View();
        }

        [HttpPost]
        public async Task<IActionResult> TrackBike(Bike bike)
        {
            if (ModelState.IsValid)
            {
                _context.Add(bike);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index), "Home");
            }
            return View(bike);
        }
    }
}
