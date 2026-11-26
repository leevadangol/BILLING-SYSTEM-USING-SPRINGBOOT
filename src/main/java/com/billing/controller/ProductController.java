package com.billing.controller;

import com.billing.model.Product;
import com.billing.model.ProductForm;
import com.billing.repository.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProductController {

    private final ProductRepository products;

    public ProductController(ProductRepository products) {
        this.products = products;
    }

    private List<Product> allProducts() {
        return products.findAll(Sort.by("id"));
    }

    // ---------- Add Item ----------

    @GetMapping("/items/add")
    public String addForm(Model model) {
        model.addAttribute("title", "Add Item");
        model.addAttribute("form", new ProductForm());
        return "add_item";
    }

    @PostMapping("/items/add")
    public String add(ProductForm form, Model model, RedirectAttributes redirect) {
        List<String> errors = form.validate();
        if (!errors.isEmpty()) {
            model.addAttribute("title", "Add Item");
            model.addAttribute("errors", errors);
            model.addAttribute("form", form);
            return "add_item";
        }
        form.setId(null);
        products.save(form.toProduct());
        redirect.addFlashAttribute("success", "Product added successfully.");
        return "redirect:/items/add";
    }

    // ---------- Update Item ----------

    @GetMapping("/items/update")
    public String updateForm(@RequestParam(required = false) Integer id, Model model) {
        ProductForm form = new ProductForm();
        if (id != null) {
            form = products.findById(id).map(ProductForm::from).orElse(new ProductForm());
        }
        return updatePage(model, form, null);
    }

    @PostMapping("/items/update")
    public String update(ProductForm form, Model model, RedirectAttributes redirect) {
        if (form.getId() == null || form.getId() <= 0 || !products.existsById(form.getId())) {
            return updatePage(model, form, List.of("Please select a product from the table first."));
        }
        List<String> errors = form.validate();
        if (!errors.isEmpty()) {
            return updatePage(model, form, errors);
        }
        products.save(form.toProduct());
        redirect.addFlashAttribute("success", "Product updated successfully.");
        return "redirect:/items/update";
    }

    private String updatePage(Model model, ProductForm form, List<String> errors) {
        model.addAttribute("title", "Update Item");
        model.addAttribute("products", allProducts());
        model.addAttribute("form", form);
        if (errors != null) {
            model.addAttribute("errors", errors);
        }
        return "update_item";
    }

    // ---------- Delete Item ----------

    @GetMapping("/items/delete")
    public String deleteList(Model model) {
        model.addAttribute("title", "Delete Item");
        model.addAttribute("products", allProducts());
        return "delete_item";
    }

    @PostMapping("/items/delete")
    public String delete(@RequestParam(required = false) Integer id, RedirectAttributes redirect) {
        if (id == null || id <= 0) {
            redirect.addFlashAttribute("error", "Please select a product to delete.");
        } else {
            products.deleteById(id);
            redirect.addFlashAttribute("success", "Product deleted successfully.");
        }
        return "redirect:/items/delete";
    }

    // ---------- Report ----------

    @GetMapping("/report")
    public String report(Model model) {
        List<Product> list = allProducts();
        model.addAttribute("title", "Product Report");
        model.addAttribute("products", list);
        model.addAttribute("total", list.size());
        return "report";
    }
}
