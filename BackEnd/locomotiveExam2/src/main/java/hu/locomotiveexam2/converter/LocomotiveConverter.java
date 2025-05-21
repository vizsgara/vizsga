package hu.locomotiveexam2.converter;

import hu.locomotiveexam2.dto.LocomotiveListItem;
import hu.locomotiveexam2.dto.LocomotiveRead;
import hu.locomotiveexam2.dto.LocomotiveSave;
import hu.locomotiveexam2.model.Locomotive;

import java.util.ArrayList;
import java.util.List;

public class LocomotiveConverter {
    public static List<LocomotiveListItem> convertModelsToList(List<Locomotive> locomotives) {
        List<LocomotiveListItem> locomotiveListItems = new ArrayList<>();
        locomotives.forEach(locomotive -> {locomotiveListItems.add(convertModelToListItem(locomotive));});
        return locomotiveListItems;
    }

    private static LocomotiveListItem convertModelToListItem(Locomotive locomotive) {
        LocomotiveListItem locomotiveListItem = new LocomotiveListItem();
        locomotiveListItem.setName(locomotive.getName());
        locomotiveListItem.setDriving(locomotive.getDriving());
        return locomotiveListItem;
    }

    public static LocomotiveRead convertModelToRead(Locomotive locomotive) {
        LocomotiveRead locomotiveRead = new LocomotiveRead();
        locomotiveRead.setId(locomotive.getId());
        locomotiveRead.setName(locomotive.getName());
        locomotiveRead.setDriving(locomotive.getDriving());
        locomotiveRead.setLengthCm(locomotive.getLengthCm());
        locomotiveRead.setMaxSpeed(locomotive.getMaxSpeed());
        return locomotiveRead;
    }

    public static Locomotive convertSaveToModel(LocomotiveSave save) {
        Locomotive locomotive = new Locomotive();
        locomotive.setName(save.getName());
        locomotive.setDriving(save.getDriving());
        locomotive.setMaxSpeed(save.getMaxSpeed());
        locomotive.setLengthCm(save.getLengthCm());
        return locomotive;
    }
}

