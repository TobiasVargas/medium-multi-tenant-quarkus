package br.dev.tobias.infra.tenant;

import io.quarkus.hibernate.orm.PersistenceUnitExtension;
import io.quarkus.hibernate.orm.runtime.tenant.TenantResolver;
import io.vertx.ext.web.RoutingContext;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PersistenceUnitExtension
@RequestScoped
public class CustomTenantResolver implements TenantResolver {
    @Inject
    RoutingContext context;
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomTenantResolver.class);

    @Override
    public String getDefaultTenantId() {
        return "default";
    }

    @Override
    public String resolveTenantId() {
        String path = context.request().path();
        String[] parts = path.split("/");
        if (parts.length == 0) {
            return getDefaultTenantId();
        }
        LOGGER.info("Resolvendo tenant: {}", parts[1]);
        return parts[1];
    }
}
