/*
 * Copyright (C) 2007-2019 Crafter Software Corporation. All Rights Reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.craftercms.studio.test.api2.objects;

import org.apache.http.HttpStatus;
import org.craftercms.studio.test.api.objects.BaseAPI;
import org.craftercms.studio.test.utils.APIConnectionManager;
import org.craftercms.studio.test.utils.JsonTester;
import org.hamcrest.collection.IsEmptyCollection;

import static org.hamcrest.Matchers.*;

public class MarketplaceAPI2 extends BaseAPI {

    private String MARKETPLACE_SEARCH_URL = "/studio/api/2/marketplace/search";
    private String PLUGIN_BLUEPRINT = "blueprint";
    private String PLUGIN_NOEXISTING = "none";
    private String[] SEARCH_PLUGIN_BLUEPRINT_KEYWORDS = {"pizza", "artshowcase"};
    private String offset = "5";
    private String limit = "7";

    public MarketplaceAPI2(JsonTester api, APIConnectionManager apiConnectionManager) {
        super(api, apiConnectionManager);
    }

    public void testGetBlueprintPlugin() {
        api.get(MARKETPLACE_SEARCH_URL).urlParam("type", PLUGIN_BLUEPRINT).execute().status(HttpStatus.SC_OK)
                .json("$.response.message", is("OK"))
                .json("$.total", not(0))
                .json("$.plugins[0].type", is("blueprint"))
                .json("$.plugins[0].id", is("org.craftercms.blueprint.videoCenter"))
                .json("$.plugins[0].name", is("Video Center"))
                .json("$.plugins[0].version.major", not(empty()))
                .json("$.plugins[0].version.minor", not(empty()))
                .json("$.plugins[0].version.patch", not(empty()))
                .json("$.plugins[0].description", containsString("Video Center webapp built with React atop Crafter CMS that is backed by YouTube, AWS Media Service, or Box"))
                .json("$.plugins[0].website.url", is("https://github.com/craftercms/video-center-blueprint"))
                .json("$.plugins[0].website.name", is("Video Center"))
                .json("$.plugins[0].media.screenshots[0].title", is("Home page"))
                .json("$.plugins[0].media.screenshots[0].description", is("Screenshot of the home page"))
                .json("$.plugins[0].media.screenshots[0].url", is("https://raw.githubusercontent.com/craftercms/video-center-blueprint/master/static-assets/images/screenshots/vc1.png"))
                .json("$.plugins[0].developer.company.name", is("Crafter Software"))
                .json("$.plugins[0].developer.company.email", is("info@craftersoftware.com"))
                .json("$.plugins[0].developer.company.url", is("https://craftersoftware.com"))
                .json("$.plugins[0].build.id", not(empty()))
                .json("$.plugins[0].build.date", not(empty()))
                .json("$.plugins[0].license.url", is("https://opensource.org/licenses/MIT"))
                .json("$.plugins[0].license.name", is("MIT"))
                .json("$.plugins[0].crafterCmsEditions[0]", is("community"))
                .json("$.plugins[0].crafterCmsEditions[1]", is("enterprise"))
                .json("$.plugins[0].crafterCmsVersions[0].major", is(3))
                .json("$.plugins[0].crafterCmsVersions[0].minor", is(1))
                .json("$.plugins[0].crafterCmsVersions[0].patch", is(4))
                .json("$.plugins[0].searchEngine", is("Elasticsearch"))
                .json("$.plugins[0].url", is("https://github.com/craftercms/video-center-blueprint"))
                .json("$.plugins[2].type", is("blueprint"))
                .json("$.plugins[2].id", is("com.rivetlogic.craftercms.blueprint.hotelinfo"))
                .json("$.plugins[2].name", is("Hotel Website"))
                .json("$.plugins[2].version.major", not(empty()))
                .json("$.plugins[2].version.minor", not(empty()))
                .json("$.plugins[2].version.patch", not(empty()))
                .json("$.plugins[2].description", containsString("Hotel Website is a stylish multi-page Crafter CMS blueprint with video backgrounds"))
                .json("$.plugins[2].website.url", is("https://github.com/rivetlogic/craftercms-bp-hotelinfo.git"))
                .json("$.plugins[2].website.name", is("Hotel Website"))
                .json("$.plugins[2].media.screenshots[0].title", is("Home Page"))
                .json("$.plugins[2].media.screenshots[0].description", is("Screenshot of the home page"))
                .json("$.plugins[2].media.screenshots[0].url", is("https://raw.githubusercontent.com/rivetlogic/craftercms-bp-hotelinfo/master/static-assets/images/screenshots/bp_hotelinfo_home.png"))
                .json("$.plugins[2].developer.company.name", is("Rivet Logic"))
                .json("$.plugins[2].developer.company.email", is("info@rivetlogic.com"))
                .json("$.plugins[2].developer.company.url", is("https://rivetlogic.com"))
                .json("$.plugins[2].build.id", not(empty()))
                .json("$.plugins[2].build.date", not(empty()))
                .json("$.plugins[2].license.url", is("https://opensource.org/licenses/MIT"))
                .json("$.plugins[2].license.name", is("MIT"))
                .json("$.plugins[2].crafterCmsEditions[0]", is("community"))
                .json("$.plugins[2].crafterCmsEditions[1]", is("enterprise"))
                .json("$.plugins[2].crafterCmsVersions[0].major", is(3))
                .json("$.plugins[2].crafterCmsVersions[0].minor", is(1))
                .json("$.plugins[2].crafterCmsVersions[0].patch", is(4))
                .json("$.plugins[2].searchEngine", is("Elasticsearch"))
                .json("$.plugins[2].url", is("https://github.com/rivetlogic/craftercms-bp-hotelinfo"));
    }

    public void testGetBlueprintNoExistingPlugin() {
        api.get(MARKETPLACE_SEARCH_URL).urlParam("type", PLUGIN_NOEXISTING).execute().status(HttpStatus.SC_OK)
                .json("$.response.message", is("OK"))
                .json("$.total", is(0))
                .json("$.plugins", IsEmptyCollection.empty());
    }

    public void testGetBlueprintsByKeyword(){
        api.get(MARKETPLACE_SEARCH_URL).urlParam("type", PLUGIN_BLUEPRINT).urlParam("keywords", SEARCH_PLUGIN_BLUEPRINT_KEYWORDS[0])
                .execute().status(HttpStatus.SC_OK)
                .json("$.response.message", is("OK"))
                .json("$.total", is(1))
                .json("$.plugins", hasSize(1))
                .json("$.plugins[0].type", is("blueprint"))
                .json("$.plugins[0].id", is("com.rivetlogic.craftercms.blueprint.pizza"))
                .json("$.plugins[0].name", is("Pizza Shop"))
                .json("$.plugins[0].version.major", not(empty()))
                .json("$.plugins[0].version.minor", not(empty()))
                .json("$.plugins[0].version.patch", not(empty()))
                .json("$.plugins[0].description", containsString("Pizza Shop!"))
                .json("$.plugins[0].website.url", is("https://github.com/rivetlogic/craftercms-bp-pizza.git"))
                .json("$.plugins[0].website.name", is("Pizza Shop"))
                .json("$.plugins[0].media.screenshots[0].title", is("Home Section"))
                .json("$.plugins[0].media.screenshots[0].description", is("Screenshot of the home section"))
                .json("$.plugins[0].media.screenshots[0].url", is("https://raw.githubusercontent.com/rivetlogic/craftercms-bp-pizza/master/static-assets/images/screenshots/bp_pizza_home.png"))
                .json("$.plugins[0].developer.company.name", is("Rivet Logic"))
                .json("$.plugins[0].developer.company.email", is("info@rivetlogic.com"))
                .json("$.plugins[0].developer.company.url", is("https://rivetlogic.com"))
                .json("$.plugins[0].build.id", not(empty()))
                .json("$.plugins[0].build.date", not(empty()))
                .json("$.plugins[0].license.url", is("https://opensource.org/licenses/MIT"))
                .json("$.plugins[0].license.name", is("MIT"))
                .json("$.plugins[0].crafterCmsEditions[0]", is("community"))
                .json("$.plugins[0].crafterCmsEditions[1]", is("enterprise"))
                .json("$.plugins[0].crafterCmsVersions[0].major", is(3))
                .json("$.plugins[0].crafterCmsVersions[0].minor", is(1))
                .json("$.plugins[0].crafterCmsVersions[0].patch", is(4))
                .json("$.plugins[0].searchEngine", is("Elasticsearch"))
                .json("$.plugins[0].url", is("https://github.com/rivetlogic/craftercms-bp-pizza"));
    }

    public void testGetBlueprintsByMultiplesKeyword(){
        api.get(MARKETPLACE_SEARCH_URL).urlParam("type", PLUGIN_BLUEPRINT).urlParam("keywords",
                SEARCH_PLUGIN_BLUEPRINT_KEYWORDS[0] + " " + SEARCH_PLUGIN_BLUEPRINT_KEYWORDS[1])
                .execute().status(HttpStatus.SC_OK)
                .json("$.response.message", is("OK"))
                .json("$.total", is(2))
                .json("$.plugins", hasSize(2))
                .json("$.plugins[0].type", is("blueprint"))
                .json("$.plugins[0].id", is("com.rivetlogic.craftercms.blueprint.artshowcase"))
                .json("$.plugins[0].name", is("Art Showcase"))
                .json("$.plugins[0].version.major", not(empty()))
                .json("$.plugins[0].version.minor", not(empty()))
                .json("$.plugins[0].version.patch", not(empty()))
                .json("$.plugins[0].description", containsString("Art Showcase is a stylish single page Crafter CMS blueprint with video backgrounds."))
                .json("$.plugins[0].website.url", is("https://github.com/rivetlogic/craftercms-bp-artshowcase.git"))
                .json("$.plugins[0].website.name", is("Art Showcase"))
                .json("$.plugins[0].media.screenshots[0].title", is("Home Page"))
                .json("$.plugins[0].media.screenshots[0].description", is("Screenshot of the home page"))
                .json("$.plugins[0].media.screenshots[0].url", is("https://raw.githubusercontent.com/rivetlogic/craftercms-bp-artshowcase/master/static-assets/images/screenshots/bp_artshowcase_homepage.png"))
                .json("$.plugins[0].developer.company.name", is("Rivet Logic"))
                .json("$.plugins[0].developer.company.email", is("info@rivetlogic.com"))
                .json("$.plugins[0].developer.company.url", is("https://rivetlogic.com"))
                .json("$.plugins[0].build.id", not(empty()))
                .json("$.plugins[0].build.date", not(empty()))
                .json("$.plugins[0].license.url", is("https://opensource.org/licenses/MIT"))
                .json("$.plugins[0].license.name", is("MIT"))
                .json("$.plugins[0].crafterCmsEditions[0]", is("community"))
                .json("$.plugins[0].crafterCmsEditions[1]", is("enterprise"))
                .json("$.plugins[0].crafterCmsVersions[0].major", is(3))
                .json("$.plugins[0].crafterCmsVersions[0].minor", is(1))
                .json("$.plugins[0].crafterCmsVersions[0].patch", is(4))
                .json("$.plugins[0].searchEngine", is("Elasticsearch"))
                .json("$.plugins[0].url", is("https://github.com/rivetlogic/craftercms-bp-artshowcase"))
                .json("$.plugins[1].type", is("blueprint"))
                .json("$.plugins[1].id", is("com.rivetlogic.craftercms.blueprint.pizza"))
                .json("$.plugins[1].name", is("Pizza Shop"))
                .json("$.plugins[1].version.major", not(empty()))
                .json("$.plugins[1].version.minor", not(empty()))
                .json("$.plugins[1].version.patch", not(empty()))
                .json("$.plugins[1].description", containsString("Pizza Shop!"))
                .json("$.plugins[1].website.url", is("https://github.com/rivetlogic/craftercms-bp-pizza.git"))
                .json("$.plugins[1].website.name", is("Pizza Shop"))
                .json("$.plugins[1].media.screenshots[0].title", is("Home Section"))
                .json("$.plugins[1].media.screenshots[0].description", is("Screenshot of the home section"))
                .json("$.plugins[1].media.screenshots[0].url", is("https://raw.githubusercontent.com/rivetlogic/craftercms-bp-pizza/master/static-assets/images/screenshots/bp_pizza_home.png"))
                .json("$.plugins[1].developer.company.name", is("Rivet Logic"))
                .json("$.plugins[1].developer.company.email", is("info@rivetlogic.com"))
                .json("$.plugins[1].developer.company.url", is("https://rivetlogic.com"))
                .json("$.plugins[1].build.id", not(empty()))
                .json("$.plugins[1].build.date", not(empty()))
                .json("$.plugins[1].license.url", is("https://opensource.org/licenses/MIT"))
                .json("$.plugins[1].license.name", is("MIT"))
                .json("$.plugins[1].crafterCmsEditions[0]", is("community"))
                .json("$.plugins[1].crafterCmsEditions[1]", is("enterprise"))
                .json("$.plugins[1].crafterCmsVersions[0].major", is(3))
                .json("$.plugins[1].crafterCmsVersions[0].minor", is(1))
                .json("$.plugins[1].crafterCmsVersions[0].patch", is(4))
                .json("$.plugins[1].searchEngine", is("Elasticsearch"))
                .json("$.plugins[1].url", is("https://github.com/rivetlogic/craftercms-bp-pizza"));
    }

    public void testGetBlueprintOffsetLimit(){
        api.get(MARKETPLACE_SEARCH_URL).urlParam("type", PLUGIN_BLUEPRINT).urlParam("offset", offset)
                .urlParam("limit", limit).execute().status(HttpStatus.SC_OK)
                .json("$.response.message", is("OK"))
                .json("$.offset", is(Integer.valueOf(offset)))
                .json("$.limit", is(Integer.valueOf(limit)))
                .json("$.plugins", hasSize(2))
                .json("$.plugins[0].type", is("blueprint"))
                .json("$.plugins[0].id", is("com.rivetlogic.craftercms.blueprint.fitness"))
                .json("$.plugins[0].name", is("Fitness"))
                .json("$.plugins[0].version.major", not(empty()))
                .json("$.plugins[0].version.minor", not(empty()))
                .json("$.plugins[0].version.patch", not(empty()))
                .json("$.plugins[0].description", containsString("Fitness is a Crafter CMS blueprint for a Fitness Club site"))
                .json("$.plugins[0].website.url", is("https://github.com/rivetlogic/craftercms-bp-fitness.git"))
                .json("$.plugins[0].website.name", is("Fitness"))
                .json("$.plugins[0].media.screenshots[0].title", is("Home Section"))
                .json("$.plugins[0].media.screenshots[0].description", is("Screenshot of the home section"))
                .json("$.plugins[0].media.screenshots[0].url", is("https://raw.githubusercontent.com/rivetlogic/craftercms-bp-fitness/master/static-assets/images/screenshots/bp_fitness_home.png"))
                .json("$.plugins[0].developer.company.name", is("Rivet Logic"))
                .json("$.plugins[0].developer.company.email", is("info@rivetlogic.com"))
                .json("$.plugins[0].developer.company.url", is("https://rivetlogic.com"))
                .json("$.plugins[0].build.id", not(empty()))
                .json("$.plugins[0].build.date", not(empty()))
                .json("$.plugins[0].license.url", is("https://opensource.org/licenses/MIT"))
                .json("$.plugins[0].license.name", is("MIT"))
                .json("$.plugins[0].crafterCmsEditions[0]", is("community"))
                .json("$.plugins[0].crafterCmsEditions[1]", is("enterprise"))
                .json("$.plugins[0].crafterCmsVersions[0].major", is(3))
                .json("$.plugins[0].crafterCmsVersions[0].minor", is(1))
                .json("$.plugins[0].crafterCmsVersions[0].patch", is(4))
                .json("$.plugins[0].searchEngine", is("Elasticsearch"))
                .json("$.plugins[0].url", is("https://github.com/rivetlogic/craftercms-bp-fitness"))
                .json("$.plugins[1].type", is("blueprint"))
                .json("$.plugins[1].id", is("com.rivetlogic.craftercms.blueprint.brochure"))
                .json("$.plugins[1].name", is("Simple Brochureware Site"))
                .json("$.plugins[1].version.major", not(empty()))
                .json("$.plugins[1].version.minor", not(empty()))
                .json("$.plugins[1].version.patch", not(empty()))
                .json("$.plugins[1].description", containsString("Single Page Brochure"))
                .json("$.plugins[1].website.url", is("https://github.com/rivetlogic/craftercms-bp-singlepage-brochure.git"))
                .json("$.plugins[1].website.name", is("Simple Brochureware Site"))
                .json("$.plugins[1].media.screenshots[0].title", is("Home Section"))
                .json("$.plugins[1].media.screenshots[0].description", is("Screenshot of the home section"))
                .json("$.plugins[1].media.screenshots[0].url", is("https://raw.githubusercontent.com/rivetlogic/craftercms-bp-singlepage-brochure/master/static-assets/img/screenshots/bp_singlepage-brochure_home.png"))
                .json("$.plugins[1].developer.company.name", is("Rivet Logic"))
                .json("$.plugins[1].developer.company.email", is("info@rivetlogic.com"))
                .json("$.plugins[1].developer.company.url", is("https://rivetlogic.com"))
                .json("$.plugins[1].build.id", not(empty()))
                .json("$.plugins[1].build.date", not(empty()))
                .json("$.plugins[1].license.url", is("https://opensource.org/licenses/MIT"))
                .json("$.plugins[1].license.name", is("MIT"))
                .json("$.plugins[1].crafterCmsEditions[0]", is("community"))
                .json("$.plugins[1].crafterCmsEditions[1]", is("enterprise"))
                .json("$.plugins[1].crafterCmsVersions[0].major", is(3))
                .json("$.plugins[1].crafterCmsVersions[0].minor", is(1))
                .json("$.plugins[1].crafterCmsVersions[0].patch", is(4))
                .json("$.plugins[1].searchEngine", is("Elasticsearch"))
                .json("$.plugins[1].url", is("https://github.com/rivetlogic/craftercms-bp-singlepage-brochure"));
    }

    public void testGetBlueprintPluginUnauthorized() {
        api.get(MARKETPLACE_SEARCH_URL).urlParam("type", PLUGIN_BLUEPRINT).execute()
                .status(HttpStatus.SC_UNAUTHORIZED);
    }
}
