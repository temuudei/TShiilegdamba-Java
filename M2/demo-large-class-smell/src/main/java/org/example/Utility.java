package org.example;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utility {
    public String formatDateTime(LocalDateTime dt) {
        return dt.format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm"));
    }

    public String formatPatientForListOutput(Visit patientVisit) {
        StringBuilder result = new StringBuilder();
        Patient patient = patientVisit.getPatient();
        Provider provider = patientVisit.getProvider();

        result.append(formatDateTime(patientVisit.getAppointmentTime())).append(" - ");
        result.append(patient.getPatientLastName()).append(", ").append(patient.getPatientFirstName()).append(" ");
        result.append("for ").append(provider.getProviderTitle()).append(" ").append(provider.getProviderLastName()).append(" - ");
        result.append(patientVisit.getVisitCost()). append(": ").append(patientVisit.isCostPaid() ? "PAID" : "DUE");

        return result.toString();
    }

    public BigDecimal calculateTotalOutstandingPayments(Visit[] visits) {
        BigDecimal result = new BigDecimal(0);

        for (Visit pv : visits) {
            if (!pv.isCostPaid()) {
                result = result.add(pv.getVisitCost());
            }
        }

        return result;
    }

    public BigDecimal calculateTotalReceived(Visit[] visits) {
        BigDecimal result = new BigDecimal(0);

        for (Visit pv : visits) {
            if (pv.isCostPaid()) {
                result = result.add(pv.getVisitCost());
            }
        }

        return result;
    }

    public Visit[] getVisitsByProviderId(String providerId, Visit[] visits) {
        Visit[] collector = new Visit[visits.length];
        int nextCollectorIndex = 0;

        for (Visit pv : visits) {
            if (pv.getProvider().getProviderId().equals(providerId)) {
                collector[nextCollectorIndex] = pv;
                nextCollectorIndex++;
            }
        }

        Visit[] results = new Visit[nextCollectorIndex];
        for (int i = 0; i < nextCollectorIndex; i++) {
            results[i] = collector[i];
        }
        return results;
    }

    public Visit[] getVisitsByPatientId(String patientId, Visit[] visits) {
        Visit[] collector = new Visit[visits.length];
        int nextCollectorIndex = 0;

        for (Visit pv : visits) {
            if (pv.getPatient().getPatientId().equals(patientId)) {
                collector[nextCollectorIndex] = pv;
                nextCollectorIndex++;
            }
        }

        Visit[] results = new Visit[nextCollectorIndex];
        for (int i = 0; i < nextCollectorIndex; i++) {
            results[i] = collector[i];
        }
        return results;
    }

}