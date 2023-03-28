package com.inspitech.dosy.views;


import com.inspitech.dosy.components.appnav.AppNav;
import com.inspitech.dosy.components.appnav.AppNavItem;
import com.inspitech.dosy.views.about.AboutView;
import com.inspitech.dosy.views.adresseslivraison.AdressesLivraisonView;
import com.inspitech.dosy.views.articles.ArticlesView;
import com.inspitech.dosy.views.articlevariants.ArticleVariantsView;
import com.inspitech.dosy.views.commandes.CommandesView;
import com.inspitech.dosy.views.connexion.ConnexionView;
import com.inspitech.dosy.views.dashboard.DashboardView;
import com.inspitech.dosy.views.dotations.DotationsView;
import com.inspitech.dosy.views.déconnexion.DéconnexionView;
import com.inspitech.dosy.views.modificationemployé.ModificationEmployéView;
import com.inspitech.dosy.views.offres.OffresView;
import com.inspitech.dosy.views.transferts.TransfertsView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.vaadin.lineawesome.LineAwesomeIcon;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private H2 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        H1 appName = new H1("Dosy 1.0");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private AppNav createNavigation() {
        // AppNav is not yet an official component.
        // For documentation, visit https://github.com/vaadin/vcf-nav#readme
        AppNav nav = new AppNav();

        nav.addItem(new AppNavItem("Dashboard", DashboardView.class, LineAwesomeIcon.ARCHWAY_SOLID.create()));
        nav.addItem(new AppNavItem("Dotations", DotationsView.class, LineAwesomeIcon.DATABASE_SOLID.create()));
        nav.addItem(new AppNavItem("Articles", ArticlesView.class, LineAwesomeIcon.DRUM_STEELPAN_SOLID.create()));
        nav.addItem(new AppNavItem("Offres", OffresView.class, LineAwesomeIcon.ETHERNET_SOLID.create()));
        nav.addItem(new AppNavItem("Commandes", CommandesView.class, LineAwesomeIcon.EMPIRE.create()));
        nav.addItem(new AppNavItem("Transferts", TransfertsView.class, LineAwesomeIcon.DICE_FOUR_SOLID.create()));
        nav.addItem(new AppNavItem("Modification Employé", ModificationEmployéView.class,
                LineAwesomeIcon.USER_EDIT_SOLID.create()));
        nav.addItem(new AppNavItem("Article Variants", ArticleVariantsView.class,
                LineAwesomeIcon.ARROW_CIRCLE_UP_SOLID.create()));
        nav.addItem(new AppNavItem("Adresses Livraison", AdressesLivraisonView.class,
                LineAwesomeIcon.MAP_MARKER_SOLID.create()));
        nav.addItem(new AppNavItem("Connexion", ConnexionView.class, LineAwesomeIcon.USER_SOLID.create()));
        nav.addItem(new AppNavItem("Déconnexion", DéconnexionView.class, LineAwesomeIcon.USER.create()));
        nav.addItem(new AppNavItem("About", AboutView.class, LineAwesomeIcon.COLUMNS_SOLID.create()));

        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
