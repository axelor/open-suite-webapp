package com.axelor.gradle.tasks.changelog;

import java.util.Arrays;
import java.util.Optional;

public enum ModuleName {
  UNKNOWN("unknown", "Unknown"),
  BASE("axelor-base", "Base"),
  ACCOUNT("axelor-account", "Account"),
  BANK_PAYMENT("axelor-bank-payment", "Bank Payment"),
  BUDGET("axelor-budget", "Budget"),
  BUSINESS_PRODUCTION("axelor-business-production", "Business Production"),
  BUSINESS_PROJECT("axelor-business-project", "Business Project"),
  BUSINESS_SUPPORT("axelor-business-support", "Business Support"),
  CASH_MANAGEMENT("axelor-cash-management", "Cash Management"),
  CLIENT_PORTAL("axelor-client-portal", "Client Portal"),
  CONTRACT("axelor-contract", "Contract"),
  CRM("axelor-crm", "CRM"),
  FLEET("axelor-fleet", "Fleet"),
  GDPR("axelor-gdpr", "GDPR"),
  HELPDESK("axelor-helpdesk", "Helpdesk"),
  HUMAN_RESOURCE("axelor-human-resource", "Human Resource"),
  MAINTENANCE("axelor-maintenance", "Maintenance"),
  MARKETING("axelor-marketing", "Marketing"),
  MOBILE_SETTINGS("axelor-mobile-settings", "Mobile Settings"),
  PRODUCTION("axelor-production", "Production"),
  PROJECT("axelor-project", "Project"),
  PURCHASE("axelor-purchase", "Purchase"),
  QUALITY("axelor-quality", "Quality"),
  SALE("axelor-sale", "Sale"),
  STOCK("axelor-stock", "Stock"),
  SUPPLIER_MANAGEMENT("axelor-supplier-management", "Supplier Management"),
  SUPPLIER_PORTAL("axelor-supplier-portal", "Supplier Portal"),
  SUPPLYCHAIN("axelor-supplychain", "Supply Chain"),
  TALENT("axelor-talent", "Talent"),
  INTERVENTION("axelor-intervention", "Intervention");

  private final String code;

  private final String title;

  ModuleName(String code, String title) {
    this.code = code;
    this.title = title;
  }

  public static Optional<ModuleName> fromCode(String code) {
    return Arrays.stream(values()).filter(m -> m.code.equals(code)).findAny();
  }

  public String getCode() {
    return code;
  }

  public String getTitle() {
    return title;
  }
}
