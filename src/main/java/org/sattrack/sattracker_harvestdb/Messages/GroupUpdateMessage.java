package org.sattrack.sattracker_harvestdb.Messages;

import java.io.Serializable;

public record GroupUpdateMessage(String groupQuery) implements Serializable { }
